<%@ page import="logh.Coach" %>



<div class="fieldcontain ${hasErrors(bean: coachInstance, field: 'teams', 'error')} ">
	<label for="teams">
		<g:message code="coach.teams.label" default="Teams" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${coachInstance?.teams?}" var="t">
    <li><g:link controller="team" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="team" action="create" params="['coach.id': coachInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'team.label', default: 'Team')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: coachInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="coach.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${users}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${coachInstance?.user?.id}" class="many-to-one"/>
</div>

