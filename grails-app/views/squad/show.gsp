
<%@ page import="logh.Squad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'squad.label', default: 'Squad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-squad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-squad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list squad">
			
				<g:if test="${squadInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="squad.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${squadInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${squadInstance?.abbrev}">
				<li class="fieldcontain">
					<span id="abbrev-label" class="property-label"><g:message code="squad.abbrev.label" default="Abbrev" /></span>
					
						<span class="property-value" aria-labelledby="abbrev-label"><g:fieldValue bean="${squadInstance}" field="abbrev"/></span>
					
				</li>
				</g:if>
			
			</ol>
            <g:if test="${session?.user?.admin}">
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${squadInstance?.id}" />
					<g:link class="edit" action="edit" id="${squadInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </g:if>
		</div>
	</body>
</html>
