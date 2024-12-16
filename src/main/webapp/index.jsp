<%--
    Document   : index
    Author     : Rajnish, Ritik and Rugung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>Welcome to Client Side File Encryption for Secure Cloud Storage</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table  cellspacing="0" cellpadding="0" align="center" border="0" class="body_content">
            <tr>
                <td colspan="2"  class="header">
                    <%@include file="header.jsp" %>
                </td>
            </tr>
            <tr>
                <td class="content">
                    <table cellpadding="3" cellspacing="3" width="100%">
                        <tr>
                            <td>
                                <div class="expand">
                                    <span>About the project</span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="span" style="line-height: 20px;">
                                <p>Cloud computing has revolutionized data storage by providing scalable and remote access to information. However, the security of sensitive data stored in the cloud remains a significant concern, especially with risks like data breaches, unauthorized access, and Distributed Denial of Service (DDoS) attacks. This project addresses these issues by implementing a <strong>Client-Side File Encryption</strong> solution for secure cloud storage, where the customer retains full control over the encryption keys and performs encryption and decryption outside the cloud provider’s system.</p>
                                <p>In this system, data is <strong>encrypted by the customer before being uploaded to the cloud.</strong> The cloud provider only stores the encrypted data and has no access to the encryption keys or plaintext data. The project employs a <strong>hybrid encryption model, combining <strong>Elliptic Curve Cryptography (ECC)</strong> for secure user authentication and <strong>Advanced Encryption Standard (AES-256)</strong> for client-side data encryption.</strong> This ensures that only authenticated users can access the cloud, while the encrypted data remains inaccessible to unauthorized individuals.</p>
                                <p>The proposed solution offers <strong>maximum security and privacy</strong> by ensuring that the cloud provider cannot decrypt or access the customer’s data. However, it requires the customer to have expertise in encryption techniques, as well as a mechanism to handle potential key loss. This solution is particularly beneficial for storing highly sensitive information such as financial or medical records. By providing <strong>secure file sharing, <strong>multi-tier architecture, and <strong>cloud compatibility</strong>, the project aims to create a secure, efficient, and scalable system that addresses cloud security challenges while ensuring ease of access for authorized users.</strong></strong></p>
                                <p>
                                    <strong>Software as a Service (SaaS)</strong><br/>
                                    The cloud provider deploys, configures, maintains, and updates the operation of the software applications on a cloud infrastructure so that the services are provisioned at the expected service levels to cloud consumers. The cloud consumers have limited administrative control of the applications.
                                </p>
                                <p>
                                    <strong>Platform as a Service (PaaS)</strong><br/>
                                    The Cloud Provider manages the computing infrastructure for the platform and runs the cloud software that provides the components of the platform, such as runtime software execution stack, databases, and other middleware components. The PaaS Cloud Provider typically also supports the development, deployment, and management process of the PaaS Cloud Consumer by providing tools such as integrated development environments (IDEs), development version of cloud software, software development kits (SDKs), deployment, and management tools.
                                </p>
                                <p>
                                    <strong>Infrastructure as a Service (IaaS)</strong><br/>
                                    The Cloud Provider acquires the physical computing resources underlying the service, including the servers, networks, storage, and hosting infrastructure. The Cloud Provider runs the cloud software necessary to make computing resources available to the IaaS Cloud Consumer through a set of service interfaces and computing resource abstractions, such as virtual machines and virtual network interfaces.
                                </p>
                            </td>

                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td class="footer">
                    <%@include file="footer.jsp" %>
                </td>
            </tr>
        </table>
    </body>
</html>
