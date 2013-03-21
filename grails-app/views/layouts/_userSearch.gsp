<div id="search">
    <g:form id="userSearchForm" name="userSearchForm" url="[controller: 'user', action: 'search']" method="get">
        <g:textField name="q" value="${params.q}"/>
        <input type="submit" value="Find a user"/>
    </g:form>
</div>