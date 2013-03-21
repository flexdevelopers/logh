$('#spinner').ajaxStart(function() {
    $(this).fadeIn();
}).ajaxStop(function() {
    $(this).fadeOut();
});

$(document).ready(function() {
    $('#week').on('change', function(event) {
        $('#game').attr('disabled', 'disabled'); // disable games drop down
        resetGames();
        if ($(this).val() != 'null') {
            retrieveGames($(this).val()); // get the games for the selected week
        }
    });

    $('#game').on('change', function(event) {
        $('#loser').attr('disabled', 'disabled'); // disable the losers drop down
        resetLosers();
        if ($(this).val() != 'null') {
            retrieveSquads($(this).val());
        }
    });

    $('#league').on('change', function(event) {
        $('#leaguePassword').val('');
    });
});

function retrieveGames(weekId) {
    $.ajax('/week/ajaxGetGames', {
        dataType: 'json',
        data: { id: weekId },
        success: showGames
    });
}

function retrieveSquads(gameId) {
    $.ajax('/game/ajaxGetSquads', {
        dataType: 'json',
        data: { id: gameId },
        success: showSquads
    });
}

function showGames(json) {
    var options = '<option value="null">Select One...</option>';
    $.each(json, function(index, game) {
        options += '<option value="' + game.id + '">' + game.name + '</option>';
    });
    $('#game').html(options);

    $('#game').removeAttr('disabled'); // finally...enable it
}

function showSquads(json) {
    var options = '<option value="null">Select One...</option>';
    $.each(json, function(index, squad) {
        options += '<option value="' + squad.id + '">' + squad.name + '</option>';
    });
    $('#loser').html(options);

    $('#loser').removeAttr('disabled'); // finally...enable it

}

function resetGames() {
    $('#game').html('<option value="null">Select One...</option>');
    $('#game').val({'null':'Select One...'}); // reset game drop-down
    $('#game').trigger('change');
}

function resetLosers() {
    $('#loser').html('<option value="null">Select One...</option>');
    $('#loser').val({'null':'Select One...'}); // reset loser drop-down
}
