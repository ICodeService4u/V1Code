from http.server import SimpleHTTPRequestHandler, HTTPServer
import json
import psycopg2

class MyHandler(SimpleHTTPRequestHandler):

    def do_POST(self):
        if self.path == '/create_account':
            self.handle_create_account()
        else:
            self.send_error(404, "Endpoint not found")

    def do_GET(self):
        if self.path == '/customers':
            self.handle_get_customers()
        else:
            self.send_error(404, "Endpoint not found")

    def handle_create_account(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        
        print("Received POST data: ", post_data)  # Log the raw POST data
        
        try:
            data = json.loads(post_data.decode('utf-8'))
            print("Parsed JSON data: ", data)  # Log the parsed JSON data
            
            # Validate the fields
            name = data.get('name')
            address = data.get('address')
            phone = data.get('phone')
            email = data.get('email')

            if not all([name, address, phone, email]):
                response = {'message': 'Missing required fields'}
                self.send_response(400)
                print(response)
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
                    print(response)
                except Exception as e:
                    cur.close()
                    conn.close()
                    response = {'message': 'Account creation failed', 'error': str(e)}
                    self.send_response(400)
                    print(response)
        except json.JSONDecodeError as e:
            print("JSON decode error: ", e)  # Log JSON decode errors
            response = {'message': 'Invalid JSON'}
            self.send_response(400)
            print(response)
        except Exception as e:
            print("Exception: ", e)  # Log general exceptions
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)
            print(response)

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
            print("Exception: ", e)  # Log general exceptions
            response = {'message': 'Internal server error', 'error': str(e)}
            self.send_response(500)

        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(response).encode())

def get_db_connection():
    conn = psycopg2.connect(
        host="localhost",
        database="myapp",
        user="your_db_user",
        password="your_db_password"
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
