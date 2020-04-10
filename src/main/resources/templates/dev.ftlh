<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
    <title>Hello, world!</title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="/">TRITON</a>
    <a class="nav-item nav-link" href="/listdev">ListDevice</a>
    <a class="nav-item nav-link" href="/dev">Device</a>

    <form class="form-inline" method="post" action="/search" enctype="multipart/form-data">
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="day" id="exampleRadios1" value="1" checked>
            <label class="form-check-label" for="exampleRadios1">
                Day
            </label>
        </div>
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="month" id="exampleRadios2" value="2" >
            <label class="form-check-label" for="exampleRadios2">
                Month
            </label>
        </div>
        <div class="form-check mr-2">
            <input class="form-check-input" type="checkbox" name="year" id="exampleRadios3" value="3" >
            <label class="form-check-label" for="exampleRadios3">
                Year
            </label>
        </div>
        <input class="form-control mr-sm-2" type="date" placeholder="Search" aria-label="Search" name="search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>

    <#list devices as dev>
        <form method="post" action="/updateDevice/${dev.id}" enctype="multipart/form-data">
        <input type="text" aria-label="First name" class="form-control ml-2" value="${dev.quantity?string("############")}" name="quantity">
        <select class="form-control" id="exampleFormControlSelect1" name="listDevice">
            <#list listDevices as listDevices>
                <option value="${listDevices.id}">${listDevices.name} ${listDevices.number?string("############")}</option>
            </#list>
        </select>
            <button class="btn btn-primary ml-2" type="submit">Save</button></form>
            <form method="post" action="/deleteDevice/${dev.id}" enctype="multipart/form-data">
                <button type="submit" class="btn btn-secondary ml-2 mr-2">Delete</button></form>
    </#list>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>