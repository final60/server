<#include '../../shared/header.ftl' />

<@pageHeader title='Dashboards' uri='/configuration/fragments' />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <#if item??>
					<form name="dashboard" id="updateDashboardScreenForm" action="/configuration/dashboards/update" method="post">

                        <input type="hidden" value="${item.id}" name="id">

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">External Reference*</label></div>
                            <div class="col-10"><input name="externalReference" class="form-control no-padding" type="text" value="${item.externalReference!''}" placeholder="External reference" <#if item.externalReference?has_content>readonly</#if> required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Uri*</label></div>
                            <div class="col-10"><input name="uri" class="form-control no-padding" type="text" value="${item.uri!''}" placeholder="Uri" <#if item.uri?has_content>readonly</#if> required></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Content*</label></div>
                            <div class="col-10"><textarea id="content" name="content">${item.content!''}</textarea></div>
                        </div>

                        <div class="form-group row">
							<#assign checked = item.enabled?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Enabled</label></div>
                            <div class="col-10"><input name="enabled" type="checkbox" ${checked}></div>
                        </div>

                        <@commonMeta item=item/>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button id="updateDashboardScreensButton" type="submit" class="btn btn-primary btn-sm float-right">Update</button>
                        <a href="/configuration/dashboards"><button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Cancel</button></a>
                    </form>
                    <#else>
					<div class="alert alert-warning">There is no property information to display</div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />