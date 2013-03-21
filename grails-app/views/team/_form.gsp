<%@ page import="logh.Team" %>



<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="team.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${teamInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="team.description.label" default="Description" />
	</label>
	<g:textField name="description" maxlength="100" value="${teamInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'league', 'error')} required">
    <g:if test="${params.action == 'create' || params.action == 'save'}">
    <label for="league">
		<g:message code="team.league.label" default="League" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="league" name="league.id" from="${logh.League.list()}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${teamInstance?.league?.id}" class="many-to-one"/>
    </g:if>
    <g:if test="${params.action == 'edit' || params.action == 'update'}">
    <span id="league-label" class="property-label"><g:message code="team.league.label" default="League" /></span>
    <span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${teamInstance?.league?.id}">${teamInstance?.league?.encodeAsHTML()}</g:link></span>
    </g:if>
</div>

<g:if test="${checkLeaguePassword}">
<div class="fieldcontain">
    <label for="leaguePassword">
        <g:message code="team.league.password.label" default="League Password"/>
    </label>
    <g:field type="password" id="leaguePassword" name="leaguePassword"/>
</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'coach', 'error')} required">
	<label for="coach">
		<g:message code="team.coach.label" default="Coach" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="coach" name="coach.id" from="${coaches}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${teamInstance?.coach?.id}" class="many-to-one"/>
</div>

<g:if test="${params.action == 'edit' || params.action == 'update'}">
<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'picks', 'error')} ">
	<label for="picks">
		<g:message code="team.picks.label" default="Picks" />
	</label>
    <ul class="one-to-many">
        <g:each in="${teamInstance?.picks?}" var="p">
        <li><g:link controller="pick" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add"><g:link controller="pick" action="create" params="['team.id': teamInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pick.label', default: 'Pick')])}</g:link></li>
    </ul>
</div>
</g:if>

