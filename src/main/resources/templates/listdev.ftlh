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
    <a class="nav-item nav-link" href="/listdev">ListDevice</a>
    <a class="nav-item nav-link" href="/dev">Device</a>

</nav>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">quantity</th>
        <th scope="col">name</th>
        <th scope="col">save</th>
        <th scope="col">delete</th>
    </tr>
    </thead>
    <tbody>
    <#list listDevices as list>
        <tr>
           <form method="post" action="/updateListDevice/${list.id}" enctype="multipart/form-data">
               <td>  <input type="text" aria-label="First name"
                            class="form-control ml-2" value="${list.number?string("############")}" name="number"> </td>
               <td>    <input type="text" aria-label="First name" class="form-control ml-2" value="${list.name}" name="name"> </td>
                <td>    <button class="btn btn-primary ml-2" type="submit">Save</button></td>
           </form>
            <td> <form method="post" action="/deleteListDevice/${list.id}" enctype="multipart/form-data">
                    <button type="submit" class="btn btn-secondary ml-2 mr-2">Delete</button></form></td>
        </tr>
    </#list>
    </tbody>
</table>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>