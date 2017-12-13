/*global WildRydes _config*/

var WildRydes = window.WildRydes || {};
WildRydes.map = WildRydes.map || {};

(function rideScopeWrapper($) {
    var authToken;
    WildRydes.authToken.then(function setAuthToken(token) {
// if (token) {
// authToken = token;
// } else {
// window.location.href = '/signin.html';
// }
    }).catch(function handleTokenError(error) {
        alert(error);
        window.location.href = '/signin.html';
    });
    function getAllListings() {
        $.ajax({
            method: 'GET',
            url: _config.api.invokeUrl + '/listing/list',
            headers: {
                Authorization: authToken
            },
            success: showListings,
            error: function ajaxError(jqXHR, textStatus, errorThrown) {
                console.error('Error getting listings: ', textStatus, ', Details: ', errorThrown);
                console.error('Response: ', jqXHR.responseText);
                alert('An error occured while getting listings:\n' + jqXHR.responseText);
            }
        });
    }

    function showListings(result) {
        console.log('Response received from API: ', result);
        $.each(result, function(i,data) {
            var listData = "<li><a href=#>"+data.id+"</a></li>";
            $('#listings').append(listData);
         });
    }

    // Register click handler for #request button
    $(function onDocReady() {
        $('#listings').on("click", "a", getListingDetail);
        $( "#createListingForm" ).dialog({
            autoOpen: false
          });
        
        $( "#createListing" ).on( "click", function() {
            $( "#createListingForm" ).dialog( "open" );
          });
        
        $( "#createBidForm" ).dialog({
            autoOpen: false
          });
        
        $( "#createBid" ).on( "click", function() {
            $( "#createBidForm" ).dialog( "open" );
          });
        
        WildRydes.authToken.then(function updateAuthMessage(token) {
            if (token) {
                displayUpdate('You are authenticated. Click to see your <a href="#authTokenModal" data-toggle="modal">auth token</a>.');
                $('.authToken').text(token);
            }
        });
        if (!_config.api.invokeUrl) {
            $('#noApiMessage').show();
        }
        getAllListings();
    });
    
    function getListingDetail(event) {
    	event.preventDefault();
      var listingId  = $( this ).text();
      $.ajax({
          method: 'GET',
          url: _config.api.invokeUrl + '/listing/'+listingId,
          headers: {
              Authorization: authToken
          },
          success: showListingDetail,
          error: function ajaxError(jqXHR, textStatus, errorThrown) {
              console.error('Error getting listing detail: ', textStatus, ', Details: ', errorThrown);
              console.error('Response: ', jqXHR.responseText);
              alert('An error occured while getting listing detail:\n' + jqXHR.responseText);
          }
      });
      
    }
    
    function showListingDetail(result) {
        console.log('Response received from API: ', result);
        $('#createdBy').text(result.createdById);
        $('#modifiedBy').text(result.modifiedById);
        $('#price').text(result.pricePerMonth);
        $( "#listingDetail" ).show();  
    }

    function displayUpdate(text) {
        $('#updates').append($('<li>' + text + '</li>'));
    }
}(jQuery));
