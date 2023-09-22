<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <html>
    <head>
      <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
      <title>Task List Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
            <a class="navbar-brand m-1" href="https://www.google.com">Google</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/tasks">Todos</a></li>
                </ul>
            </div>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </nav>

        <div class="container">
            <h1 style="border:2px solid Blue;">Welcome To List of the Tasks</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td>${task.description}</td>
                            <td>${task.endDate}</td>
                            <td>${task.done}</td>
                            <td><a href="deleteTask?id=${task.id}" class="btn btn-warning">Delete</a></td>
                            <td><a href="updateTask?id=${task.id}" class="btn btn-success">Update</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="addTask" class="btn btn-success">Add Task</a>
        </div>
      <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
      <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    </body>
    
 </html>
