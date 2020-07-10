<#include "../include/imports.ftl">

<#-- @ftlvariable name="document" type="org.example.beans.ContentDocument" -->
<#if document??>
    <article class="has-edit-button">
    <@hst.manageContent hippobean=document />
    <h3>${document.title?html}</h3>
    <#if document.publicationDate??>
        <p>
        <@fmt.formatDate value=document.publicationDate.time type="both" dateStyle="medium" timeStyle="short"/>
        </p>
    </#if>
    <#if document.introduction??>
        <p>
        ${document.introduction?html}
        </p>
    </#if>
    <@hst.html hippohtml=document.content/>
    <div>
    <ul>
    <#list document.images as img>
        <img width="300" src="${img}" />
    </#list>
    </ul>
    </div>
    <#if document.infographics??>
        <script id="infogram_0_${document.infographics.path}" title="Ocean Sand" src="https://e.infogram.com/js/dist/embed.js?YSX" type="text/javascript"></script>
    </#if>
    </article>
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#elseif editMode>
    <div class="has-edit-button">
    <img src="<@hst.link path="/images/essentials/catalog-component-icons/simple-content.svg" />"> Click to edit Simple Content
    <@hst.manageContent documentTemplateQuery="new-content-document" parameterName="document" rootPath="content"/>
    </div>
</#if>
