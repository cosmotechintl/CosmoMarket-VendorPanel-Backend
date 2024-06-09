-- liquibase formatted sql
--changeset amritkthapa:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO product (name, price, description, brand, size, color, in_stock, quantity, category_id, image, updated_by)
VALUES ('Jeans pant', 100.00, ' Blue Jeans Pant of Xl size', "puma", "XL", "black", true, 5, 1, 'image1.jpg', 'amritkthapa'),
       (' Knife', 200.00, 'Kitchen knife of large size', "baltra", " ", "black", true, 20, 2, 'image2.jpg', 'amritkthapa'),
       (' T-Shirt', 300.00, 'Black T-shirt of small size', "Nike", "L", "Red", true, 30, 1, 'image3.jpg', 'amritkthapa');