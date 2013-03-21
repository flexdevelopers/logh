<%@ page import="logh.Commish" %>



<div class="fieldcontain ${hasErrors(bean: commishInstance, field: 'leagues', 'error')} ">
	<label for="leagues">
		<g:message code="commish.leagues.label" default="Leagues" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${commishInstance?.leagues?}" var="l">
    <li><g:link controller="league" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="league" action="create" params="['commish.id': commishInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'league.label', default: 'League')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: commishInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="commish.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${users}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${commishInstance?.user?.id}" class="many-to-one"/>
</div>

