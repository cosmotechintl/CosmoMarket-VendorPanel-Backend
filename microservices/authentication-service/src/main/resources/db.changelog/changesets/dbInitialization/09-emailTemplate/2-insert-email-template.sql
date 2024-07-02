-- liquibase formatted sql
--changeset kirankhanal:1

--preconditions onFail:CONTINUE onError:HALT

INSERT INTO `email_template` (name, template, created_date, version)
VALUES
    (
     'Vendor User Verification', '<!DOCTYPE html>\n<html lang="en">\n<head>\n    <meta charset="UTF-8">\n    <title>Account Verification</title>\n    <style>\n        body {\n            font-family: Arial, sans-serif;\n            background-color: #f4f4f4;\n            margin: 0;\n            padding: 0;\n        }\n        .container {\n            width: 100%;\n            max-width: 600px;\n            margin: 0 auto;\n            background-color: #ffffff;\n            padding: 20px;\n            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n        }\n        .header {\n            text-align: center;\n            padding: 10px 0;\n            background-color: #007bff;\n            color: #ffffff;\n        }\n        .content {\n            padding: 20px;\n            line-height: 1.6;\n            text-align: justify;\n        }\n        .btn {\n            display: inline-block;\n            background-color: #007bff;\n            color: #ffffff;\n            padding: 10px 20px;\n            text-decoration: none;\n            border-radius: 5px;\n        }\n        .footer {\n            text-align: center;\n            padding: 10px 0;\n            color: #888888;\n            font-size: 12px;\n        }\n    </style>\n</head>\n<body>\n<div class="container">\n    <div class="header">\n        <h1>Account Verification</h1>\n    </div>\n    <div class="content">\n        <p>Dear ${vendorUserName},</p>\n        <p>Your CosmoMarket vendor account has been created. To complete your registration, please verify your email address by clicking the button below:</p>\n        <p style="text-align: center;">\n            <a href="${verificationLink}" class="btn">Verify Account</a>\n        </p>\n        <p>If the button above does not work, please copy and paste the following link into your browser:</p>\n        <p><a href="${verificationLink}">Link</a></p>\n        <p>This verification link will expire in ${expirationTime?string("yyyy-MM-dd HH:mm:ss")}
        hours.</p>\n        <p>If you did not create an account with us, please ignore this email.</p>\n        <p>Best regards,<br/>Cosmotech International Pvt. Ltd.</p>\n    </div>\n    <div class="footer">\n        <p>&copy; ${currentYear} Cosmotech International Pvt. Ltd. All rights reserved.</p>\n    </div>\n</div>\n</body>\n</html>',
     NOW(),
     0
    );
