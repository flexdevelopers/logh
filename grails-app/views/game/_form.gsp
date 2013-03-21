<%@ page import="logh.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'home', 'error')} required">
	<label for="home">
		<g:message code="game.home.label" default="Home" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="home" name="home.id" from="${logh.Squad.list()}" optionKey="id" required="" value="${gameInstance?.home?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'visitor', 'error')} required">
	<label for="visitor">
		<g:message code="game.visitor.label" default="Visitor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="visitor" name="visitor.id" from="${logh.Squad.list()}" optionKey="id" required="" value="${gameInstance?.visitor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'loser', 'error')} ">
	<label for="loser">
		<g:message code="game.loser.label" default="Loser" />
		
	</label>
	<g:select id="loser" name="loser.id" from="${logh.Squad.list()}" optionKey="id" value="${gameInstance?.loser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'week', 'error')} required">
	<label for="week">
		<g:message code="game.week.label" default="Week" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="week" name="week.id" from="${logh.Week.list()}" optionKey="id" required="" value="${gameInstance?.week?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="game.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${gameInstance?.startDate}"  />
</div>

