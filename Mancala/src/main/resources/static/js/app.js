var HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open( "GET", aUrl, true );
        anHttpRequest.send( null );
        }
 }


function play(index) {
        var url = "http://"+ window.location.hostname   + ":8080/play/" + index.toString();
        var client = new HttpClient();

        client.get(url, function(response) {
             $('#board').html(response);

        });
}


function resetButton() {
        var url = "http://"+ window.location.hostname   + ":8080/reset/";
        var client = new HttpClient();

        client.get(url, function(response) {
            $('#board').html(response);

        });

}

