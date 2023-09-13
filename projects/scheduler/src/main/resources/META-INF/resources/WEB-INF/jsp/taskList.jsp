<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <html>
    <head>
      <title>Login Page</title>
    </head>
    <body>
      <h1 style="border:2px solid Blue;">Welcome To List of the Tasks</h1>
      Hello ${name}
      <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.description}</td>
                        <td>${task.endDate}</td>
                        <td>${task.done}</td>
                    </tr>
                </c:forEach>
            </tbody>
      </table>
    </body>
    
 </html>
