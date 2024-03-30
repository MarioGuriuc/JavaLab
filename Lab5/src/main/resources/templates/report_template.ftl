<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Directory Report</title>
</head>
<body>
<h1>Directory Report for ${directoryName}</h1>

<ul>
    <#list files as file>
        <li>${file.name}</li>
    </#list>
</ul>
</body>
</html>
