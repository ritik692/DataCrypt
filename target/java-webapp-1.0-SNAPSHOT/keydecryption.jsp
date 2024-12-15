<%--
    Document   : decryption
    Author     : Rajnish, Ritik and Rugung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    String id = null;
    id = (String) session.getAttribute("ID");
    if (id != null) {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Decrypt AES Key  -  Efficient & Secure Data Storage & Access Scheme in Cloud Computing using AES</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/scw.js"></script>
        <script type="text/javascript">
            function validation() {
                var key = document.getElementById('key');
                if (key.value.trim() == "") {
                    alert('Please enter AES key');
                    key.focus();
                    return false;
                }
                var txtfile = document.getElementById('txtfile');
                if (txtfile.value.trim() == "") {
                    alert('Please select file');
                    txtfile.focus();
                    return false;
                }
            }
        </script>
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
                    <div class="form_content">
                        <form method="post" action="decryptedKey">
                            <table cellpadding="3" cellspacing="3" width="100%">
                                <tr>
                                    <td>
                                        <div class="expand">
                                            <span>Decrypt AES key</span>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    String msg = null;
                                    String fileid = request.getParameter("fileid");
                                    msg = (String) session.getAttribute("MSG");
                                    if (msg != null) {
                                %>
                                <tr>
                                    <td class="error"><%=msg%></td>
                                </tr>
                                <%
                                        session.removeAttribute("MSG");
                                    } else {
                                        session.setAttribute("MSG", "");

                                    }
                                %>
                                <tr>
                                    <td class="span">
                                        Enter the key generated on the last step to decrypt AES Key.
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" name="key" id="key" placeholder="Key" class="input"/>
                                        <input type="hidden" name="fileid" id="fileid" value="<%=fileid%>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">
                                        <input type="submit" name="btnsubmit" id="btnsubmit" value="Submit">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
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
<%    } else {
        session.setAttribute("MSG", "You must be login.");
        response.sendRedirect("login.jsp");
    }
%>