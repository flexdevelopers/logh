<g:if test="${session?.user?.admin}">
<g:render template="/layouts/userSearch"/>
</g:if>

<div id="header">
    <p><a class="header-main" href="${resource(dir: '')}">LOGH</a> </p>
    <p class="header-sub">Losing is EVERYTHING. Don't forget it.</p>
    <div id="loginHeader">
        <g:loginControl/>
    </div>
</div>