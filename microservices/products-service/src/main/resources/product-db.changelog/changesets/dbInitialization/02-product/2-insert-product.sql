-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO product (name,description, version,price,brand,color,image,size,code,in_stock,quantity,product_category,futsalStatus)
VALUES
  ('wai wai','waiwai noodles ',0,20.0,'CG','Yellow','waiwai.png','15*20',uuid(),1,100,1,1),
  ('T-Shirt','half t-shirt',0,3500.75,'ADIDAS','white','tshirt.png','XXL',uuid(),1,10,1,1);