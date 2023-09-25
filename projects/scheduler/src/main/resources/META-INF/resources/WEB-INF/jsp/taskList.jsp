        <%@ include file="common/header.jspf" %>
        <%@ include file="common/navigation.jspf" %>
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
        <%@ include file="common/footer.jspf" %>
