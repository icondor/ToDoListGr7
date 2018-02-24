

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
    <title>Question and answer application</title>

    <link rel="stylesheet" href="css.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>

<div id="left">

</div>

<div>
    <div id="listQA">
        <ol></ol>
    </div>

    </br>

    </br>


    <div class="imageContent">

        <h3 id="altaIntrebare">Alta intrebare?</h3>
        <div id ="sendSomething">
            <input type="text" id="namePerson" name="namePerson" placeholder="Intrebare" />
            <input type="text" id="response" name="response" placeholder="Raspuns" />
            <input type="button" id="add" value="Trimite"  onClick="addName()"/>
        </div>
    </div>
</div>
</body>

<script>
    // just doing an ajax call
    function loadQuestions() {
        $.ajax({
            url: 'demoRW?action=read'
        }).done(function (response) {
            putQuestionsInHTML(response.pers);
        });
    }
    function putQuestionsInHTML(ir) {
        var divQA = document.getElementById('listQA');
        var ol = document.getElementsByTagName('ol')[0];
        var list = '';
        for (var i = 0; i < ir.length; i++) {
            var question = ir[i].intrebare;
            var answer = ir[i].raspuns;
            var qa = '<li><p>' + question + '<p>' + answer + '</p>' + '</p></li>';
            list = list + qa;
        }
        ol.innerHTML = list;
    }
    loadQuestions();
    function addName() {          //trimitem date
        var question = document.getElementById('namePerson').value;
        var resp = document.getElementById('response').value;
        if(question.trim().length > 0 && resp.trim().length > 0) {
            $.ajax({
                url: 'demoRW?action=write&newName=' + question + '&r=' + resp
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var alertDiv = document.createElement("p");
            alertDiv.setAttribute("id", "alertMessage")
            var alertContent = document.createTextNode("You must insert data in both fields!");
            alertDiv.appendChild(alertContent);
            var fieldsDiv = document.getElementById("sendSomething");
            fieldsDiv.appendChild(alertDiv);
        }
    }
</script>






<p>
    <a href="logout">Logout</a>
</p>


</body>

</html>
