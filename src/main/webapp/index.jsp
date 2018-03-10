

<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if(o==null)
    {
        response.sendRedirect("login.html");
    }
%>


<body>

<head>
    <title>To Do App</title>

    <link rel="stylesheet" href="css.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>




<div id="sendSomething"></div>

        <div id ="newtodo">
            <input type="text" id="nameToDo" name="nameToDo" placeholder="do something" />
            <input type="button" id="add" value="Trimite"  onClick="addNewToDo()"/>
        </div>

        </br>

    <div id="listOfToDo">
        aici o sa fie taskurile
        <ul></ul>
    </div>



    </br>



</body>

<script>
    // just doing an ajax call
    function loadToDo() {
        $.ajax({
            url: 'tl?action=read'
        }).done(function (response) {
            putToDoInHTML(response.todo);
        });
    }
    function putToDoInHTML(todo) {

        var list = document.getElementById('listOfToDo').getElementsByTagName('ul')[0];
        var listHtml = '';

        for (var i = 0; i < todo.length; i++) {
            var task = todo[i];
            //var checked = task.done ? ' checked=""' : '';
            var taskHtml =
                '<li>' +
                '<input type="checkbox" value="' + task.id + '" onclick=markDone("' + task.id + '")>' +
                task.name +
                '</li>';
            listHtml += taskHtml;
        }
        list.innerHTML = listHtml;

    }

    loadToDo();



    function addNewToDo() {          //trimitem date
        var nametodo = document.getElementById('nameToDo').value;

        if(nametodo.trim().length > 0) {
            $.ajax({
                url: 'tl?action=write&newName=' + nametodo
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var alertDiv = document.createElement("p");
            alertDiv.setAttribute("id", "alertMessage")
            var alertContent = document.createTextNode("You must insert data!");
            alertDiv.appendChild(alertContent);
            var fieldsDiv = document.getElementById("sendSomething");
            fieldsDiv.appendChild(alertDiv);
        }
    }

    function markDone(id) {


        $.ajax({
            url: 'tl?action=markdone&id=' + id
        }).done(function (response) {
            location.href = "index.jsp";
        });


    }
</script>






<p>
    <a href="logout">Logout</a>
</p>


</body>

</html>
