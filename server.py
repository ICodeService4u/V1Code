from http.server import SimpleHTTPRequestHandler, HTTPServer
import json
import psycopg2

class MyHandler(SimpleHTTPRequestHandler):

    def do_POST(self):
        if self.path == '/create_account':
            self.handle_create_account()
        else:
            self.send_error(404, "Endpoint not found")

    def handle_create_account(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        
        print("Received POST data: ", post_data)  # Log the raw POST data
        
        try:
            data = json.loads(post_data)
            print("Parsed JSON data: ", data)  # Log the parsed JSON data
            
            name = data['name']
            address = data['address']
            phone = data['phone']
            email = data['email']

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
            print("JSON decode error: ", e)  # Log JSON decode errors
            response = {'message': 'Invalid JSON'}
            self.send_response(400)
        except KeyError as e:
            print("KeyError: ", e)  # Log missing key errors
            response = {'message': f'Missing key in JSON: {e}'}
            self.send_response(400)
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