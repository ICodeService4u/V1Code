from http.server import SimpleHTTPRequestHandler, HTTPServer
import json
import psycopg2

class MyHandler(SimpleHTTPRequestHandler):

    def do_POST(self):
        if self.path == '/create_account':
            self.handle_create_account()
        elif self.path == '/add_service':
            self.handle_add_service()
        else:
            self.send_error(404, "Endpoint not found")

    def do_GET(self):
        if self.path == '/customers':
            self.handle_get_customers()
        elif self.path == '/customer_services':
            self.handle_get_customer_services()
        else:
            self.send_error(404, "Endpoint not found")

    def handle_create_account(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        
        try:
            data = json.loads(post_data.decode('utf-8'))
            name = data.get('name')
            address = data.get('address')
            phone = data.get('phone')
            email = data.get('email')

            if not all([name, address, phone, email]):
                response = {'message': 'Missing required fields'}
                self.send_response(400)
            else:
                conn = get_db_connection()
                cur = conn.cursor()
                try:
                    cur.execute("INSERT INTO customers (name, address, phone, email) VALUES (%s, %s, %s, %s)",
                                (name, address, phone, email))
                    conn.commit()
                    cur.close()
                    conn.close()
                    response = {'message': 'Customer account created successfully!'}
                    self.send_response(200)
                except Exception as e:
                    cur.close()
                    conn.close()
                    response = {'message': 'Account creation failed', 'error': str(e)}
                    self.send_response(400)
        except json.JSONDecodeError as e:
            response = {'message': 'Invalid JSON'}
            self.send_response(400)
        except Exception as e:
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)

        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(response).encode())

    def handle_get_customers(self):
        try:
            conn = get_db_connection()
            cur = conn.cursor()
            cur.execute("SELECT id, name, address, phone, email FROM customers")
            customers = cur.fetchall()
            cur.close()
            conn.close()

            customer_list = [
                {"id": customer[0], "name": customer[1], "address": customer[2], "phone": customer[3], "email": customer[4]}
                for customer in customers
            ]

            response = {'customers': customer_list}
            self.send_response(200)
        except Exception as e:
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)

        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(response).encode())

    def handle_add_service(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        
        try:
            data = json.loads(post_data.decode('utf-8'))
            customer_id = data.get('customer_id')
            service_id = data.get('service_id')

            if not all([customer_id, service_id]):
                response = {'message': 'Missing required fields'}
                self.send_response(400)
            else:
                conn = get_db_connection()
                cur = conn.cursor()
                try:
                    cur.execute("INSERT INTO customer_services (customer_id, service_id) VALUES (%s, %s)",
                                (customer_id, service_id))
                    conn.commit()
                    cur.close()
                    conn.close()
                    response = {'message': 'Service added to customer account successfully!'}
                    self.send_response(200)
                except Exception as e:
                    cur.close()
                    conn.close()
                    response = {'message': 'Failed to add service', 'error': str(e)}
                    self.send_response(400)
        except json.JSONDecodeError as e:
            response = {'message': 'Invalid JSON'}
            self.send_response(400)
        except Exception as e:
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)

        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(response).encode())

    def handle_get_customer_services(self):
        try:
            conn = get_db_connection()
            cur = conn.cursor()
            cur.execute("""
            SELECT 
                cs.customer_id,
                c.name as customer_name,
                cs.service_id,
                s.name as service_name
            FROM 
                customer_services cs
            JOIN 
                customers c ON cs.customer_id = c.id
            JOIN 
                services s ON cs.service_id = s.id;
            """)
            customer_services = cur.fetchall()
            cur.close()
            conn.close()

            customer_services_list = [
                {"customer_id": row[0], "customer_name": row[1], "service_id": row[2], "service_name": row[3]}
                for row in customer_services
            ]

            response = {'customer_services': customer_services_list}
            self.send_response(200)
        except Exception as e:
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)

        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(response).encode())

def get_db_connection():
    conn = psycopg2.connect(
        host="localhost",
        database="myapp",
        user="postgres",
        password="miken1ke"
    )
    return conn

def test_db_connection():
    try:
        conn = get_db_connection()
        cur = conn.cursor()
        cur.execute("SELECT 1")
        cur.close()
        conn.close()
        print("Database connection test successful")
    except Exception as e:
        print(f"Database connection test failed: {e}")

def run(server_class=HTTPServer, handler_class=MyHandler, port=8000):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print(f"Starting server on port {port}")

    # Test database connection before starting the server
    test_db_connection()

    httpd.serve_forever()

if __name__ == '__main__':
    run()
