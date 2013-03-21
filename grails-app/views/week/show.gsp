
<%@ page import="logh.Week" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'week.label', default: 'Week')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-week" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-week" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list week">
				<g:if test="${weekInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="week.number.label" default="Number" /></span>
					<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${weekInstance}" field="number"/></span>
				</li>
				</g:if>
				<g:if test="${weekInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="week.name.label" default="Name" /></span>
					<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${weekInstance}" field="name"/></span>
				</li>
				</g:if>
				<g:if test="${weekInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="week.startDate.label" default="Start Date" /></span>
					<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${weekInstance?.startDate}" /></span>
				</li>
				</g:if>
				<g:if test="${weekInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="week.endDate.label" default="End Date" /></span>
					<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${weekInstance?.endDate}" /></span>
				</li>
				</g:if>
				<g:if test="${weekInstance?.games}">
				<li class="fieldcontain">
					<span id="games-label" class="property-label"><g:message code="week.games.label" default="Games" /></span>
                    <g:each in="${weekInstance.games}" var="g">
                    <span class="property-value" aria-labelledby="games-label"><g:link controller="game" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
                    </g:each>
                    <span class="property-value" aria-labelledby="picks-label"><g:link controller="game" action="create" params="['week.id': weekInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'game.label', default: 'Game')])}</g:link></span>
                </li>
				</g:if>
			</ol>
            <g:if test="${session?.user?.admin}">
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${weekInstance?.id}" />
					<g:link class="edit" action="edit" id="${weekInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </g:if>
		</div>
	</body>
</html>
