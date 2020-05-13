<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="/">TRITON</a>

    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" class="btn btn-outline-success my-2 my-sm-0" value="Sing Out">
    </form>

    <a class="nav-item nav-link" href="/listdev">ListDevice</a>
    <a class="nav-item nav-link" href="/dev">Device</a>

    <form class="form-inline" method="post" action="/search" enctype="multipart/form-data">
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="day" id="exampleRadios1" value="1" checked>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <label class="form-check-label" for="exampleRadios1">
                Day
            </label>
        </div>
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="month" id="exampleRadios2" value="2" >
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <label class="form-check-label" for="exampleRadios2">
                Month
            </label>
        </div>
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="year" id="exampleRadios3" value="3" >
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <label class="form-check-label" for="exampleRadios3">
                Year
            </label>
        </div>
        <input class="form-control mr-sm-2" type="date" placeholder="Search" aria-label="Search" name="search">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<div class="container">
<form class="form-input mt-2 mb-2" method="post" action="/createListDevice/" enctype="multipart/form-data">
    <div class="input-group">
        <input type="text" aria-label="First name" class="form-control" placeholder="name" name="name">
        <input type="number" aria-label="Last name" class="form-control" placeholder="number" name="number">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Add device</button>
    </div>
</form>
</div>
<div class="container">
<form class="form-input mt-2 mb-2" method="post" action="/createDevice/" enctype="multipart/form-data">
    <div class="input-group">

        <select class="form-control" id="exampleFormControlSelect1" name="listDevice">
            <#list listDevices as listDevices>
                <option value="${listDevices.id}">${listDevices.name} ${listDevices.number?string("############")}</option>
            </#list>
        </select>

        <input type="number" class="form-control" id="exampleFormControlInput1" placeholder="количество" name="quantity">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Add count</button>
    </div>
</form>
</div>
<div class="container">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">quantity</th>
        <th scope="col">name</th>
        <th scope="col">date</th>
    </tr>
    </thead>
    <tbody>
    <#list devices as devices>
    <tr>
        <td>${devices.quantity} шт</td>
        <td>${devices.listDevice.name} ${devices.listDevice.number?string("############")}</td>
        <td>${devices.date?string("dd.MM.yyyy")}</td>
    </tr>
    </#list>
    </tbody>
</table>
    </div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>