
<%@ page import="logh.League" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'league.label', default: 'League')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-league" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-league" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list league">
				<g:if test="${leagueInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="league.name.label" default="Name" /></span>
					<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${leagueInstance}" field="name"/></span>
				</li>
				</g:if>
				<g:if test="${leagueInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="league.description.label" default="Description" /></span>
					<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${leagueInstance}" field="description"/></span>
				</li>
				</g:if>
				<g:if test="${leagueInstance?.paid}">
				<li class="fieldcontain">
					<span id="paid-label" class="property-label"><g:message code="league.paid.label" default="Paid" /></span>
					<span class="property-value" aria-labelledby="paid-label"><g:formatBoolean boolean="${leagueInstance?.paid}" /></span>
				</li>
				</g:if>
				<g:if test="${leagueInstance?.commish}">
				<li class="fieldcontain">
					<span id="commish-label" class="property-label"><g:message code="league.commish.label" default="Commish" /></span>
                    <g:if test="${session?.user?.admin}">
					<span class="property-value" aria-labelledby="commish-label"><g:link controller="commish" action="show" id="${leagueInstance?.commish?.id}">${leagueInstance?.commish?.encodeAsHTML()}</g:link></span>
                    </g:if>
                    <g:else>
                    <span class="property-value" aria-labelledby="commish-label"><g:fieldValue bean="${leagueInstance}" field="commish"/></span>
                    </g:else>
				</li>
				</g:if>
				<g:if test="${leagueInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="league.dateCreated.label" default="Date Created" /></span>
					<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${leagueInstance?.dateCreated}" /></span>
				</li>
				</g:if>
				<g:if test="${leagueInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="league.lastUpdated.label" default="Last Updated" /></span>
					<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${leagueInstance?.lastUpdated}" /></span>
				</li>
				</g:if>
				<li class="fieldcontain">
					<span id="teams-label" class="property-label"><g:message code="league.teams.label" default="Teams" /></span>
                    <g:each in="${leagueInstance.teams}" var="t">
                        <g:if test="${session?.user?.admin}">
                        <span class="property-value" aria-labelledby="teams-label"><g:link controller="team" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                        </g:if>
                        <g:else>
                        <span class="property-value" aria-labelledby="teams-label">${t?.encodeAsHTML()}</span>
                        </g:else>
                    </g:each>
                    <g:if test="${session?.user?.admin}">
                    <span class="property-value" aria-labelledby="picks-label"><g:link controller="team" action="create" params="['league.id': leagueInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'team.label', default: 'Team')])}</g:link></span>
                    </g:if>
                </li>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${leagueInstance?.id}" />
					<g:link class="edit" action="edit" id="${leagueInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
