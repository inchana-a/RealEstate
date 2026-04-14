-- ================= BUY - FULL HOUSE =================
INSERT INTO properties
(title, description, type, listing_type, status, price, bedrooms, bathrooms, city, address, area_sqft,
 contact_name, contact_number, contact_email, state, country, latitude, longitude, created_at, nearby_places,
 owner_id, furnishing, parking)
VALUES
    ('2BHK Apartment Pune', 'Nice flat', 'APARTMENT', 'BUY', 'AVAILABLE', 4500000, 2, 2, 'Pune', 'Baner', 900,
     'Amit Sharma', '9876543210', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 18.5204, 73.8567, NOW(), 'School, Mall near Pune',
     1, 'SEMI', '2W'),

    ('3BHK Villa Pune', 'Luxury villa', 'VILLA', 'BUY', 'AVAILABLE', 9500000, 3, 3, 'Pune', 'Hinjewadi', 1800,
     'Neha Verma', '9123456789', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 18.5912, 73.7389, NOW(), 'IT Park near Pune',
     1, 'FULL', '4W'),

    ('1BHK House Mumbai', 'Small house', 'HOUSE', 'BUY', 'SOLD', 3500000, 1, 1, 'Mumbai', 'Andheri', 600,
     'Ravi Patel', '9912345678', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 19.1136, 72.8697, NOW(), 'Metro, Market near Mumbai',
     1, 'NONE', '2W'),

    ('2BHK Apartment Mumbai', 'Sea view', 'APARTMENT', 'BUY', 'AVAILABLE', 8000000, 2, 2, 'Mumbai', 'Bandra', 950,
     'Karan Mehta', '8823456789', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 19.0600, 72.8295, NOW(), 'Beach, Mall near Mumbai',
     1, 'SEMI', '4W'),

    ('3BHK House Nagpur', 'Spacious house', 'HOUSE', 'BUY', 'SOLD', 6000000, 3, 2, 'Nagpur', 'Civil Lines', 1400,
     'Priya Singh', '9734567890', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 21.1458, 79.0882, NOW(), 'Hospital near Nagpur',
     1, 'FULL', '4W'),

    ('2BHK Villa Nashik', 'Premium villa', 'VILLA', 'BUY', 'AVAILABLE', 5000000, 2, 2, 'Nashik', 'College Road', 1200,
     'Sneha Joshi', '9645678901', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 19.9975, 73.7898, NOW(), 'Temple near Nashik',
     1, 'SEMI', '2W');

-- ================= BUY - LAND =================
INSERT INTO properties
(title, description, type, listing_type, status, price, city, address, area_sqft,
 contact_name, contact_number, contact_email, state, country, latitude, longitude, created_at, nearby_places,
 owner_id, furnishing, parking)
VALUES
    ('Plot Pune', 'Residential plot', 'LAND', 'BUY', 'AVAILABLE', 3000000, 'Pune', 'Wagholi', 2000,
     'Amit Sharma', '9876543211', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 18.5800, 73.9700, NOW(), 'School near Pune',
     1, 'NONE', 'NONE'),

    ('Plot Mumbai', 'Commercial plot', 'LAND', 'BUY', 'AVAILABLE', 12000000, 'Mumbai', 'Thane', 5000,
     'Neha Verma', '9123456790', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 19.2183, 72.9781, NOW(), 'Highway near Mumbai',
     1, 'NONE', 'NONE'),

    ('Plot Nagpur', 'Open land', 'LAND', 'BUY', 'AVAILABLE', 2500000, 'Nagpur', 'MIHAN', 1500,
     'Ravi Patel', '9912345679', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 21.1200, 79.0500, NOW(), 'Airport near Nagpur',
     1, 'NONE', 'NONE'),

    ('Plot Nashik', 'Farm land', 'LAND', 'BUY', 'AVAILABLE', 2000000, 'Nashik', 'Igatpuri', 3000,
     'Karan Mehta', '8823456790', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 19.7000, 73.5600, NOW(), 'Hill view near Nashik',
     1, 'NONE', 'NONE'),

    ('Plot Thane', 'Investment plot', 'LAND', 'BUY', 'AVAILABLE', 4000000, 'Thane', 'Ghodbunder', 2200,
     'Priya Singh', '9734567891', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 19.2000, 72.9700, NOW(), 'Road near Thane',
     1, 'NONE', 'NONE');

-- ================= RENT - FULL HOUSE =================
INSERT INTO properties
(title, description, type, listing_type, status, price, bedrooms, bathrooms, city, address, area_sqft,
 contact_name, contact_number, contact_email, state, country, latitude, longitude, created_at, nearby_places,
 owner_id, furnishing, parking)
VALUES
    ('2BHK Rent Pune', 'Near IT park', 'APARTMENT', 'RENT', 'AVAILABLE', 18000, 2, 2, 'Pune', 'Kharadi', 850,
     'Sneha Joshi', '9645678902', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 18.5679, 73.9143, NOW(), 'IT Park near Pune',
     1, 'SEMI', '2W'),

    ('3BHK Rent Mumbai', 'Luxury rent', 'APARTMENT', 'RENT', 'RENTED', 55000, 3, 3, 'Mumbai', 'Powai', 1200,
     'Amit Sharma', '9876543212', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 19.1176, 72.9060, NOW(), 'Lake near Mumbai',
     1, 'FULL', '4W'),

    ('1BHK Rent Pune', 'Budget home', 'APARTMENT', 'RENT', 'AVAILABLE', 12000, 1, 1, 'Pune', 'Hadapsar', 500,
     'Neha Verma', '9123456792', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 18.5089, 73.9260, NOW(), 'Market near Pune',
     1, 'NONE', '2W'),

    ('2BHK Rent Nashik', 'Nice home', 'HOUSE', 'RENT', 'AVAILABLE', 15000, 2, 2, 'Nashik', 'CIDCO', 900,
     'Ravi Patel', '9912345680', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 19.9800, 73.7800, NOW(), 'School near Nashik',
     1, 'SEMI', '2W'),

    ('3BHK Rent Nagpur', 'Big house', 'HOUSE', 'RENT', 'RENTED', 20000, 3, 2, 'Nagpur', 'Dharampeth', 1400,
     'Karan Mehta', '8823456793', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 21.1500, 79.0800, NOW(), 'City center Nagpur',
     1, 'FULL', '4W'),

    ('Luxury Villa Rent Pune', 'High-end villa', 'VILLA', 'RENT', 'AVAILABLE', 60000, 4, 4, 'Pune', 'Wakad', 3000,
     'Priya Singh', '9734567893', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 18.6000, 73.7500, NOW(), 'Luxury area Pune',
     1, 'FULL', '4W');

-- ================= EXTRA =================
INSERT INTO properties
(title, description, type, listing_type, status, price, bedrooms, bathrooms, city, address, area_sqft,
 contact_name, contact_number, contact_email, state, country, latitude, longitude, created_at, nearby_places,
 owner_id, furnishing, parking)
VALUES
    ('3BHK Apartment Pune', 'Premium society', 'APARTMENT', 'BUY', 'AVAILABLE', 7500000, 3, 3, 'Pune', 'Wakad', 1300,
     'Sneha Joshi', '9645678904', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 18.6100, 73.7600, NOW(), 'Society near Pune',
     1, 'SEMI', '4W'),

    ('2BHK House Mumbai', 'Compact home', 'HOUSE', 'BUY', 'SOLD', 6500000, 2, 2, 'Mumbai', 'Chembur', 900,
     'Amit Sharma', '9876543213', 'triveni.suryawanshi@gmail.com', 'Maharashtra', 'India', 19.0500, 72.8800, NOW(), 'Metro near Mumbai',
     1, 'NONE', '2W'),

    ('1BHK Rent Mumbai', 'Small flat', 'APARTMENT', 'RENT', 'AVAILABLE', 20000, 1, 1, 'Mumbai', 'Ghatkopar', 400,
     'Neha Verma', '9123456794', 'shrutikamatkar1996@gmail.com', 'Maharashtra', 'India', 19.0800, 72.9100, NOW(), 'Railway near Mumbai',
     1, 'NONE', '2W');