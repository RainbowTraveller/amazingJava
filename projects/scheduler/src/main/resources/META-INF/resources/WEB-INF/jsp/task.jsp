        <%@ include file="common/header.jspf" %>
        <%@ include file="common/navigation.jspf" %>
        <div class="container">
            <h1 style="border:2px solid Blue;">Add New Task Details</h1>
            <form:form method="post" modelAttribute="task">
                <fieldset class="mb-3">
                    <form:label path="description">Details</form:label>
                    <form:input type="text" path="description" required="required"/> 
                    <form:errors path="description" cssClass="txt-warning"/>
                </fieldset>

                <fieldset class="mb-3">
                    <form:label path="endDate">Target Date</form:label>
                    <form:input type="text" path="endDate" required="required"/> 
                    <form:errors path="endDate" cssClass="txt-warning"/>
                </fieldset>

                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="done"/>
                <input type="submit" class="btn btn-success"/>
            </form:form>
        </div>
        <%@ include file="common/footer.jspf" %>
        <script type="text/javascript">
            $('#endDate').datepicker({
            format: 'yyyy-mm-dd',
            });
        </script>
