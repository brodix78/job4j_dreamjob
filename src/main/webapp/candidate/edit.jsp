<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<%@ page import="ru.job4j.dream.store.Store" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Работа мечты</title>
</head>
<body>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
  <script>
      function validate() {
          var alarm = "";
          if ($('#name').val().length < 1) {
              alarm = "name ";
          }
          if ($('#cityId').val() < 1) {
              alarm = alarm + "city ";
          }
          if ($("#photoId").val() < 1) {
              alarm = alarm + "photo ";
          }
          if (alarm != "") {
              alert("Fill field(s): " + alarm);
          }
          return (alarm == "");
      }
  </script>
  <script>
    function fillSelect(){
        $(
          $.ajax({
            url: 'http://localhost:8080/job4j_dreamjob/cities.do',
            type: 'GET'
          }).done(function(data) {
            var resp=JSON.parse(data);
            select = document.getElementById('cityId');
            for (var i=0; i!=resp.length; ++i) {
              var opt = document.createElement('option');
              opt.value = resp[i].id;
              opt.innerHTML = resp[i].name;
              select.appendChild(opt);
            }
            $("#cityId").val($('#defCity').val());
        })
      );
    }
  </script>
<%
    String id = request.getParameter("id");
    Candidate candidate = (id != null && !request.getParameter("id").equals("0"))
        ? PsqlStore.instOf().candidateById(Integer.valueOf(id)) : new Candidate(0, "");
    if (request.getParameter("photoId") != null) {
        candidate.setPhotoId(Integer.valueOf(request.getParameter("photoId")));
    }
    if (request.getParameter("name") != null) {
        candidate.setName(request.getParameter("name"));
    }
    if (request.getParameter("cityId") != null) {
        candidate.setCityId(Integer.valueOf(request.getParameter("cityId")));
    }

%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.do">Главная</a>
            </li>

        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
            <% if (id == null) { %>
                Новый кандидат
            <% } else { %>
                Редактирование данных кандидата
            <% } %>
            </div>
            <div class="card-body">
              <form action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>&photoId=<%=candidate.getPhotoId()%>" method="post" onsubmit="return validate();">
                  <div class="form-group">
                      <label>Имя</label>
                      <input type="text" class="form-control" id="name" name="name" value="<%=candidate.getName()%>">
                  </div>
                  <br/>
                  <input type="hidden" id="defCity" value="<%=candidate.getCityId()%>">
                  <script>fillSelect();</script>
                  <div class="form-froup">
                      <label for="city">Город:</label>
                      <select id="cityId" name="cityId" value="<%=candidate.getCityId()%>">
                      </select>
                  </div>
                  <div class="form-group">
                    <input type="hidden" id="photoId" value="<%=candidate.getPhotoId()%>">
                    <% if (candidate.getPhotoId() != 0) { %>
                        <br />
                        <img src="<%=request.getContextPath()%>/download?photoId=<%=candidate.getPhotoId()%>" width="100px" height="100px"/>
                        <br />
                    <% }%>
                  </div>
                  <br/>
                  <button type="submit" class="btn btn-primary">Сохранить</button>
              </form>
            </div>
            <br />
            <br />
            <form action="<%=request.getContextPath()%>/upload">
                <input type="hidden" name="action" value="select"/>
                <input type="hidden" name="id" value="<%=candidate.getId()%>"/>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">Выбрать/загрузить фотографию</button>
                </div>
            </form>
</body>
</html>
