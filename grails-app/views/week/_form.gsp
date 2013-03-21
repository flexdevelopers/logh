<%@ page import="logh.Week" %>

<div class="fieldcontain ${hasErrors(bean: weekInstance, field: 'number', 'error')} required">
	<label for="number">
		<g:message code="week.number.label" default="Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="number" type="number" min="1" value="${weekInstance.number}" required=""/>
</div>
<div class="fieldcontain ${hasErrors(bean: weekInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="week.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${weekInstance?.name}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: weekInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="week.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${weekInstance?.startDate}"  />
</div>
<div class="fieldcontain ${hasErrors(bean: weekInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="week.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${weekInstance?.endDate}"  />
</div>
<g:if test="${params.action == 'edit' || params.action == 'update'}">
<div class="fieldcontain ${hasErrors(bean: weekInstance, field: 'games', 'error')} ">
	<label for="games">
		<g:message code="week.games.label" default="Games" />
	</label>
    <ul class="one-to-many">
        <g:each in="${weekInstance?.games?}" var="g">
        <li><g:link controller="game" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add"><g:link controller="game" action="create" params="['week.id': weekInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'game.label', default: 'Game')])}</g:link></li>
    </ul>
</div>
</g:if>

