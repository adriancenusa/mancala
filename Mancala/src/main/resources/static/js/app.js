var ready = 4;
var status_ok = 200;


var HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState == ready && anHttpRequest.status == status_ok)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open( "GET", aUrl, true );
        anHttpRequest.send( null );
        }

    this.post = function(aUrl, aCallback) {
            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() {
                if (anHttpRequest.readyState == ready && anHttpRequest.status == status_ok)
                    aCallback(anHttpRequest.responseText);
            }

            anHttpRequest.open( "POST", aUrl, true );
            anHttpRequest.send( null );
            }
 }


function play(index) {
        var url = "http://"+ window.location.hostname   + ":8080/play/" + index.toString();
        var client = new HttpClient();

        client.post(url, function(response) {
             $('#board').html(response);

        });
}


function resetButton() {
        var url = "http://"+ window.location.hostname   + ":8080/reset/";
        var client = new HttpClient();

        client.post(url, function(response) {
            $('#board').html(response);

        });

}

