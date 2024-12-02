import random

# Function to generate `seats` insert statements
def generate_seats_data(total_seats=1000, price_range=(15, 50)):
    seat_statements = []
    seat_id = 1
    screening_prices = {}  # To store the price for each screening
    
    for screening_id in range(1, 11):  # Assuming 10 screenings
        # Random price for each screening
        price = round(random.uniform(price_range[0], price_range[1]), 2)
        screening_prices[screening_id] = price
    
    # Generate seat data with the price for each screening
    for i in range(1, total_seats + 1):
        # Get the screening ID (sequentially assigning each group of 100 seats to a screening)
        screening_id = (i - 1) // 100 + 1
        
        # Seat number repeats from 1 to 100 every 100 seats
        seat_number = (i - 1) % 100 + 1
        
        # Registered user ID is always NULL
        registered_user_id = "NULL"
        
        # Get the price for the current screening
        price = screening_prices[screening_id]
        
        # Create the SQL insert statement for the current seat
        seat_statement = f"INSERT INTO seats (id, price, seat_number, registered_user_id, screening_id) VALUES ({seat_id}, {price}, {seat_number}, {registered_user_id},{int(seat_id/100)+1});"
        seat_statements.append(seat_statement)
        
        seat_id += 1
    
    return seat_statements

# Function to generate `screenings_seat_list` insert statements
def generate_screening_seat_list(seats_per_screening=100, total_screenings=10):
    screening_seat_statements = []
    
    # Each screening will have 100 seats, and screening_id will increment from 1 to 10
    for screening_id in range(1, total_screenings + 1):
        for seat_id in range((screening_id - 1) * seats_per_screening + 1, screening_id * seats_per_screening + 1):
            screening_seat_statement = f"INSERT INTO screenings_seat_list (screening_id, seat_list_id) VALUES ({screening_id}, {seat_id});"
            screening_seat_statements.append(screening_seat_statement)
    
    return screening_seat_statements

# Main function to execute the generation and write to file
def main():
    # Generate seat data (1000 seats)
    seat_statements = generate_seats_data(total_seats=1000)
    
    # Generate screenings seat list data (each screening gets 100 seats)
    screening_seat_statements = generate_screening_seat_list(seats_per_screening=100, total_screenings=10)
    
    # Open the file to write the SQL insert statements
    with open("insert_statements.txt", "w") as f:
        # Write seat insert statements to the file
        for statement in seat_statements:
            f.write(statement + "\n")
        
        # Write screenings seat list insert statements to the file
        for statement in screening_seat_statements:
            f.write(statement + "\n")
    
    print("SQL insert statements have been written to insert_statements.txt")

if __name__ == "__main__":
    main()
