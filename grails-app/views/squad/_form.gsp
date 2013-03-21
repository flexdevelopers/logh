<%@ page import="logh.Squad" %>



<div class="fieldcontain ${hasErrors(bean: squadInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="squad.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="30" required="" value="${squadInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: squadInstance, field: 'abbrev', 'error')} required">
	<label for="abbrev">
		<g:message code="squad.abbrev.label" default="Abbrev" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="abbrev" required="" value="${squadInstance?.abbrev}"/>
</div>

