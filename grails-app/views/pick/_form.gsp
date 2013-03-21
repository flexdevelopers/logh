<%@ page import="logh.Pick" %>



<div class="fieldcontain ${hasErrors(bean: pickInstance, field: 'team', 'error')} required">

    <g:if test="${params.action == 'create' || params.action == 'save'}">
        <label for="team">
            <g:message code="pick.team.label" default="Team" />
            <span class="required-indicator">*</span>
        </label>
        <g:select id="team" name="team.id" from="${pickTeams}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${pickInstance?.team?.id}" class="many-to-one"/>
    </g:if>
    <g:if test="${params.action == 'edit' || params.action == 'update'}">
        <span id="team-label" class="property-label"><g:message code="pick.team.label" default="Team" /></span>
        <span class="property-value" aria-labelledby="team-label"><g:link controller="team" action="show" id="${pickInstance?.team?.id}">${pickInstance?.team?.encodeAsHTML()}</g:link></span>
    </g:if>
</div>

<div class="fieldcontain ${hasErrors(bean: pickInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="pick.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${logh.Week.list()}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${pickInstance?.week?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pickInstance, field: 'game', 'error')} required">
	<label for="game">
		<g:message code="pick.game.label" default="Game" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="game" name="game.id" from="${(pickInstance?.week?.id) ? logh.Game.findAllByWeek(logh.Week.get(pickInstance?.week?.id)): []}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${pickInstance?.game?.id}" class="many-to-one" disabled="${!pickInstance?.week}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pickInstance, field: 'loser', 'error')} required">
	<label for="loser">
		<g:message code="pick.loser.label" default="Loser" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="loser" name="loser.id" from="${pickSquads}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${pickInstance?.loser?.id}" class="many-to-one" disabled="${!pickInstance?.week || !pickInstance?.game}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pickInstance, field: 'correct', 'error')} ">
	<label for="correct">
		<g:message code="pick.correct.label" default="Correct" />
		
	</label>
	<g:checkBox name="correct" value="${pickInstance?.correct}" disabled="${!isAdmin}" />
</div>

