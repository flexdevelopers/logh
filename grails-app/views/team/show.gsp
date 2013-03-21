
<%@ page import="logh.Team" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-team" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list team">
				<g:if test="${teamInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="team.name.label" default="Name" /></span>
					<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${teamInstance}" field="name"/></span>
				</li>
				</g:if>
				<g:if test="${teamInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="team.description.label" default="Description" /></span>
					<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${teamInstance}" field="description"/></span>
				</li>
				</g:if>
				<g:if test="${teamInstance?.league}">
				<li class="fieldcontain">
					<span id="league-label" class="property-label"><g:message code="team.league.label" default="League" /></span>
					<span class="property-value" aria-labelledby="league-label"><g:link controller="league" action="show" id="${teamInstance?.league?.id}">${teamInstance?.league?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
				<g:if test="${teamInstance?.coach}">
				<li class="fieldcontain">
					<span id="coach-label" class="property-label"><g:message code="team.coach.label" default="Coach" /></span>
                    <g:if test="${session?.user?.admin}">
					<span class="property-value" aria-labelledby="coach-label"><g:link controller="coach" action="show" id="${teamInstance?.coach?.id}">${teamInstance?.coach?.encodeAsHTML()}</g:link></span>
                    </g:if>
                    <g:else>
                    <span class="property-value" aria-labelledby="coach-label"><g:fieldValue bean="${teamInstance}" field="coach"/></span>
                    </g:else>
				</li>
				</g:if>
				<g:if test="${teamInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="team.dateCreated.label" default="Date Created" /></span>
					<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${teamInstance?.dateCreated}" /></span>
				</li>
				</g:if>
				<g:if test="${teamInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="team.lastUpdated.label" default="Last Updated" /></span>
					<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${teamInstance?.lastUpdated}" /></span>
				</li>
				</g:if>
				<li class="fieldcontain">
					<span id="picks-label" class="property-label"><g:message code="team.picks.label" default="Picks" /></span>
                    <g:each in="${teamInstance.picks}" var="p">
                    <span class="property-value" aria-labelledby="picks-label"><g:link controller="pick" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
                    </g:each>
                    <span class="property-value" aria-labelledby="picks-label"><g:link controller="pick" action="create" params="['team.id': teamInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pick.label', default: 'Pick')])}</g:link></span>
				</li>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${teamInstance?.id}" />
					<g:link class="edit" action="edit" id="${teamInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
