<%@ page import="logh.League" %>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="league.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${leagueInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="league.description.label" default="Description" />
	</label>
	<g:textField name="description" maxlength="100" value="${leagueInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="league.password.label" default="Password" />
    </label>
    <g:field type="password" name="password" value="${leagueInstance.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'paid', 'error')} ">
	<label for="paid">
		<g:message code="league.paid.label" default="Paid" />
	</label>
	<g:checkBox name="paid" value="${leagueInstance?.paid}" />
</div>

<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'commish', 'error')} required">
	<label for="commish">
		<g:message code="league.commish.label" default="Commish" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="commish" name="commish.id" from="${commishes}" noSelection="${['null':'Select One...']}" optionKey="id" required="" value="${leagueInstance?.commish?.id}" class="many-to-one"/>
</div>

<g:if test="${params.action == 'edit' || params.action == 'update'}">
<div class="fieldcontain ${hasErrors(bean: leagueInstance, field: 'teams', 'error')} ">
	<label for="teams">
		<g:message code="league.teams.label" default="Teams" />
	</label>
    <ul class="one-to-many">
        <g:each in="${leagueInstance?.teams?}" var="t">
            <g:if test="${session?.user?.admin}">
            <li><g:link controller="team" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
            </g:if>
            <g:else>
                <li>${t?.encodeAsHTML()}</li>
            </g:else>
        </g:each>
        <g:if test="${session?.user?.admin}">
        <li class="add"><g:link controller="team" action="create" params="['league.id': leagueInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'team.label', default: 'Team')])}</g:link></li>
        </g:if>
    </ul>
</div>
</g:if>

