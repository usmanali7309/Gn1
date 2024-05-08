<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SCB</title>
<link type="text/css" href="favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link type="text/css" rel="stylesheet"
	href="Fonts/Roboto-Font/Roboto-RCondensed.css" />
<link type="text/css" rel="stylesheet"
	href="Fonts/Font-Awesome/font-awesome.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="CSS/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="CSS/style.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="CSS/bootstrap-datetimepicker.css" />



<script type="text/javascript" src="JS/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="JS/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/inline-script.js"></script>
<script type="text/javascript" src="JS/tableHeadFixer.js"></script>

<script type="text/javascript" src="JS/moment.js"></script>
<script type="text/javascript" src="JS/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="app_js/messages_text.js"></script>
<script type="text/javascript" src="app_js/opportunitylist_controller.js"></script>
<script type="text/javascript" src="app_js/filesplitter_controller.js"></script>
<script type="text/javascript" src="app_js/filterlogic_controller.js"></script>
<script type="text/javascript" src="app_js/archive_controller.js"></script>
<script type="text/javascript" src="app_js/fieldmapping_controller.js"></script>

<script type="text/javascript" src="app_js/splitreport_controller.js"></script>
<script type="text/javascript" src="app_js/fieldmask_controller.js"></script>

<script type="text/javascript" src="app_js/main.js"></script>


<script>var project_name = "${pageContext.request.contextPath}";</script>

<!-----------Table Head Fixed----------->
<script>
	$(document).ready(function() {
		$(".tabledata-container").tableHeadFixer({'foot' : true, 'head' : true});
		$(".table-container").tableHeadFixer({'foot' : true, 'head' : true});
		
		
	});
</script>
<!-----------Table Head Fixed----------->

<script type="text/javascript" src="JS/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="JS/dataTables.bootstrap.min.js"></script>

</head>


<body>
<div id="loader"></div>
	<header>
	
		<div class="header-container">
			<div class="pull-left header-left">
			<div class="info-msg2" >
						  <i class="fa fa-info-circle"></i>
						<label id="File_format_lable_id" style="margin: -5px 0 0 0; padding: 1px 0 0 0;" >File Format : - </label></br>
						<label id="File_name_lable_id" style="font-size: 11px;padding: 2px 0 0 0;" >File Name : - </label>
						</div>
			</div>

			<div class="pull-right header-left">
						
					
						<p   class="pull-left tenant-name"><i class="fa fa-globe"></i> <span id="tenant_lable_id">Tenant : -</span> </p>
			
			
				<a href="#" class="logo pull-right"><img src="./Images/logo.jpg"
					alt="Standard Charted" title="Standard Charted" /></a>				
				<h3 class="pull-left logo-head">Spliter</h3>
				
						
			</div>
			<label id="Error_lable_id" style="text-align: center;color: red;display: flow-root;margin: 8px 254px;font-size: 14px;"></label>					
		</div>
	</header>
	
	<section class="section-container">
		<div class="tab-container">
			<ul class="nav nav-tabs tabs" >
				<li class="contact-list-tab active" onclick="dictonary_tab_click()"><a data-toggle="tab"
					href="#contact-list-tab">Opportunity List Import</a></li>
				<li class="file-split-tab"><a data-toggle="tab"
					id="file_split_tab_id" href="#file-split-tab">File Split</a></li>
				<li class="create-filter-tab" onclick="click_filter_tab_act()"><a data-toggle="tab"
					href="#create-filter-tab">Filter Logic</a></li>
				<li class="archive-tab" onclick="archive_tab_click()"><a data-toggle="tab"
					href="#archive-tab">Archive</a></li>
					
				<li class="fieldmapping-tab" onclick="filed_map_tab_act()"><a data-toggle="tab"
					id="is_" href="#fieldmapping-tab">Field Mapping</a></li>
				
				<li class="contact-list-tab"><a data-toggle="tab"
					id="fieldname_tab_id" href="#fieldname-tab">Snapshot</a></li>
					
				<li class="splitreport-tab" onclick="split_report_tab_act()"><a data-toggle="tab"
					id="is_split_report" href="#audit-subfile-summary-tab">Tenant Split Report</a></li>
					
					
				<li class="configuration-tab" style="display:none"><a data-toggle="tab"
					href="#configuration-tab">Configuration</a></li>
			</ul>
			<div class="tab-content">
				<div id="contact-list-tab" class="tab-pane fade in active">
					<div class="tab-body-container">
					
					
						<div class="form-body-container contact-list-form" style="width: 500px; min-height: 178x;">
							<div style="width:0px;float:right;margin:10px -15px 0 0;">
								<button data-toggle="tab" style="float: left ! important;" id="redirect_fieldmap_id"  href="#fieldmapping-tab" class="submit-icon-btn green-btn pull-right" 
								onclick="redirect_filedmap_tab()" disabled="disabled"><i class="fa fa-plus"></i>Mapping</button>
								
							</div>
							
							<ul class="form-container">
								<li>
									<div class="label-field">
										<label>Dictonary Files</label>
									</div>
									<div class="input-field" style="padding: 2px;border-color: #e2e2e2 !important;">
										<select class="input-select2" id="filename_listID" onchange="dictonaryPath_onchange()" onmousedown="dictionary_onmousedoun();">
										<!-- <option>Select</option>   --> 
										</select>
									</div>
								</li>
								<li>
									<div class="label-field">
										<label>Archive Files</label>
									</div>
									<div class="input-field" style="padding: 2px;border-color: #e2e2e2 !important;">
										<select class="input-select2" id="archive_file_listID" onchange="archivePath_onchange()" onmousedown="archive_onmousedoun()">
										<!-- <option>Select</option> -->
										</select>
									</div>
								</li>
								<li>
									<div class="label-field">
										<label>Delimiter</label>
									</div>
									<div class="input-field" style="padding: 2px;">
										<select class="input-select2" id="delimiter_id" onchange="delimiter_onchange()">
											<option value="0">Please select a input delimiter</option>
<!-- 											<option selected="selected">|</option>
 -->										<!-- 	<option>#</option>
											<option>,</option>
											<option>$</option>
											<option>-</option> -->
										</select>
									</div>
								</li>
								<li style ="display:none">
									<div class="label-field">
										<label>File Format Type</label>
									</div>
									<div class="input-field" style="padding: 2px;">
										<select class="input-select2" id="fileformat_ID">
										</select>
									</div>
								</li>
							</ul>
							

							<ul class="btn-container1" style="padding-bottom: 0px;">
								<li>
									<button class="submit-icon-btn blue-btn preview-btn pull-left"
										onclick="view_preview_record()">
										<i class="fa fa-eye"></i>Preview(top 100)
									</button>
								</li>
								<li>
									<button class="submit-icon-btn green-btn pull-right buttonload"
										onclick="save_dictonary_file()" id="dictonary_btn_id">
										<i class="fa fa-save"></i>Submit
									</button>
								</li>



							</ul>
						</div>
						<div id="dictionary_form_id" class="table-body-container  contact-list-container contact-list-scroll-container"
							style="display:none">
							<div class="table-inner-container ">
								<table class="table-container copytable" style="width: 100%;"
									id="csvtable" >
									<thead id="th_thead_id">
									</thead> 
									<tbody id="csvtable_tbody_id"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div id="create-filter-tab" class="tab-pane fade" >
				
					<div class="tab-body-container create-filter-container show-filter-list" >
					
						<div class="filter-list-container">
							<h3 class="filter-head">Filter List</h3>
							<ul class="filter-list" id="filter_menuID">
							</ul>
						</div>
						<div class="filter-logic-container" >	
						
						<div class="info-msg1 ">
						  <i class="fa fa-info-circle"></i>
						<label id="Error_filter_lable_id" >Total Record Count : 0</label>
						</div>			
						<div id="div-filter-label-id" class="form-body-container filter-logic-form" style="width: 600px; height: 62px;">		
														
							<ul class="form-container filter-logic-form">
							 <li style="display:none" id="li_filter_fileformat_id">
									<div class="label-field">
										<label>File Format Type</label>
									</div>
									<div class="input-field" style="padding: 2px;">
										<select class="input-select2" id="fileformat_Filter_ID" onmousedown="filter_fileformat_onmouse()" onchange="fileformat_onchange(fileformat_Filter_ID)">
										<option>Please select file format type</option>
										</select>
									</div>
								</li>
								
								<li>
									<div class="label-field">
										<label>Filter Name</label>
									</div>
									<div class="input-field" style="border: 1px solid #0078a6;">
										<input class="input-text2" type="text" id="FilterName_id" placeholder='Please enter Filter name'/>
									</div>
								</li>
							</ul>
							<ul class='action-container1' style="cursor:pointer; margin:-35px -40px 0 0; float:right;">
								<li class='delete-icon' style='display:none;' onclick='delete_filter_logic()' id="delete_filterlogic_id" ><i class='fa fa-trash'></i></li>							
							</ul>
							
							
							
							
						<div style="float: right; margin: -52px -156px 0 0;">
							<button class="submit-icon-btn1 green-btn pull-left"
							style="margin-right: 10px;display:none;" onclick="new_filterLogic()" id="create_filterlogic_id">
							<i class="fa fa-plus"></i>New Filter
							</button>
							</div>
						</div>
	
						
						
						<div class="table-body-container scroll-filterlogic" >
							<div class="table-inner-container ">
								<table class="tabledata-container filter-logic-calculation" style="width: 100%;" id="FilterLogic_tableID">
									<thead>
										<tr>
											<!-- <th ><h4>Sr No</h4></th> -->
											<th><h4>Field Name</h4></th>
											<th><h4>Filter Logic</h4></th>
											<th><h4>From Value</h4></th>
											<th><h4>To Value</h4></th>
											<th><h4>Condition</h4></th>
											<!-- <th><h4>Operator</h4></th> -->
											<th id="add_btn_filterlogic_id"><h4><i class='add-filter-btn hide' onclick='add_group()'>+</i></h4></th>
										</tr>
									</thead>								
								</table>
									<button class="submit-icon-btn green-btn pull-left hidden"
									style="margin-right: 10px;" onclick="add_filter()">
									<i class="fa fa-plus"></i>Add Logic
								</button>
							</div>
						</div>
						<ul class="btn-container1 filter-logic-btn-container" style="height: 48px;">
							<li>
							   
								
								<!-- <button class="submit-icon-btn green-btn pull-left"
									style="margin-right: 10px;" onclick="new_filterLogic()" id="create_filterlogic_id">
									<i class="fa fa-plus"></i>New
								</button> -->
								<button class="submit-icon-btn orange-btn pull-left"
									style="margin-right: 10px" onclick="add_logic_group()" id="add_group_btn_id">
									<i class="fa fa-plus"></i>Add Group
								</button>
								<button class="submit-icon-btn orange-btn pull-left"
									style="margin-right: 10px" onclick="validate_filter()" id="filterlogic_val_id">
									<i class="fa fa-check-circle"></i>Validate Filter
								</button>

								<button id="Filter_preview_recordID" class="submit-icon-btn blue-btn pull-left"
									style="margin-right: 10px" onclick="view_filter_record_count();" disabled="disabled">
									<i class="fa fa-eye"></i>View Record Count
								</button>

								<button id="Filter_top_recordID" class="submit-icon-btn blue-btn  pull-left"
									style="margin-right: 10px" onclick="view_top_filter_record()" disabled="disabled">
									<i class="fa fa-eye"></i>Preview Record(top 100)
								</button>

								<button
									class="submit-icon-btn blue-btn  pull-left filter-list-btn hidden"
									style="margin-right: 10px" onclick="set_filter_list()">
									<i class="fa fa-filter"></i>Filter List
								</button>
								<!--  <button class="submit-icon-btn green-btn  pull-left" onclick="create_filterLogic()">
									<i class="fa fa-plus"></i>Create Filter
								</button> -->

								
							</li>
							<li>
								<button id="filter_saveID" class="submit-icon-btn green-btn pull-right" onclick="save_filter_act()" disabled="disabled">
									<i class="fa fa-save"></i>Save
								</button>
							</li>
						</ul>
						<div class="table-body-container  contact-list-container"
							style="height: 402px;display:none" id="filter_logic_view_recordID">
							<div class="table-inner-container ">
								<table id="Filter_tblID" class="table-container" style="width: 100%;">
									<thead id="Filter_th_id">
										
									</thead>
									<tbody id = "create_filter_tbody_id">
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					</div>
				</div>
				<div id="file-split-tab" class="tab-pane fade">
					<div class="tab-body-container">
						<div class="filter-detail-container" style="min-height: 78px;">
							<ul class="filter-detail row">
								<li>
									<div class="filter-result-detail">
										<div class="lable-field">
											<p>File Name</p>
										</div>
										<div class="detail-field">
											<span id="spliter_filename_id">-</span>
										</div>
									</div>
								</li>
								<li>
									<div class="filter-result-detail">
										<div class="lable-field">
											<p>Total records processed count</p>
										</div>
										<div class="detail-field">
											<span id="spliter_tol_record_id">0</span>
										</div>
									</div>
								</li>
								<li>
									<div class="filter-result-detail">
										<div class="lable-field">
											<p>Records count as per file info</p>
										</div>
										<div class="detail-field">
											<span id="spliter_per_record_id">0</span>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="table-body-container file-split-report-container" >
							<div class="table-inner-container ">
							<table class="tabledata-container" id="file_split_id" style="width: 100%; table-layout : fixed;">
 									<thead>
										<tr>
											<th style="width:70px"><h4>S.No</h4></th>
											<!-- <th><h4>Output File Path</h4></th> -->
											<th  style="width:200px"><h4>Choose Filter Name</h4></th>	
											<th  style="width:200px"><h4>Output File Name</h4></th>
											<th  style="width:130px"><h4>Records Count</h4></th>
											<!-- <th  style="width:100px"><h4>Edit</h4></th> -->
											<!-- <th  style="width:100px"><h4>Delete</h4></th> -->
											<th id="filesplit_add_btn" style="width:100px"><h4><i class='add-filter-btn' onclick='add_fileSplit()'>+</i></h4></th>
											
										</tr>
									</thead>
									<tbody id ="fileSplit_tbody_id">
										
									</tbody>
									<tfoot>
										<tr>
											<td colspan="4"><p>
													<strong>Total Records count of all logics</strong>
												</p></td>
											<td><p>
													<strong id="total_record_id">0</strong> 
												</p></td>
										</tr>
									</tfoot>
								</table>

							</div>
						</div>
						<ul class="btn-container1 file-split-btn-container" style="padding-bottom: 0px;">
							<li>
								<!-- <button class="submit-icon-btn green-btn pull-left" id="filesplit_add_btn"
									style="margin-right: 10px;" onclick="add_fileSplit()">
									<i class="fa fa-plus"></i>Add Logic
								</button> -->
								<button class="submit-icon-btn blue-btn pull-left" id="filesplit_valid_btn" onclick="validate_filesplit_logic()" >
									<i class="fa fa-check-circle"></i>Validate Logic
								</button>
								<button class="submit-icon-btn green-btn pull-left"
									style="margin-right: 10px;margin:0 0 0 11px;display:none"  onclick="save_filesplit_details()"  id="filesplit_btn_id" disabled="disabled">
									<i class="fa fa-save"></i>Save Logic
								</button>
								
							</li>
							<li>
								<button class="submit-icon-btn orange-btn pull-right"  id="create_sub_btn_id" onclick="create_sub_file_action()" disabled="disabled">
									<i class="fa fa-file-alt" ></i>Create Sub Files
								</button>
							</li>
						</ul>
					</div>
				</div>
				<div id="archive-tab" class="tab-pane fade">
					<div class="tab-body-container">
						<div class="form-body-container" style="width: 500px;">
							<ul class="form-container">
								<li>
									<div class="label-field" style="width:175px;">
										<label>Choose files to Archieve</label>
									</div>
									<div class="input-field multiselect-field" style="padding: 2px;border-color: #e2e2e2 !important;">
									
								
								<!-- FOR MULTI SELECTION -->
		     						 <div class="multiselect">
							  			<div class="selectBox" onclick="showCheckboxes()">
							        		<select name="category" id="category" class="input-select2" >
							      				<option id="defaultCategory" onmousedown="(function(e){ e.preventDefault(); })(event, this)">--Select--</option>
							    			</select>
							    		<div class="overSelect"></div>
							    		</div>
							  		<div id="mult_checkboxes_id" class="dropdown-content"></div>
							  		</div>
							  	</div>
														
								</li>
							</ul>

							<ul class="btn-container1" style="padding-bottom: 0px;">

								<li>
									<button class="submit-icon-btn green-btn pull-right" onclick="dictionary_file_move_action()">
										<i class="fa fa-save"></i>Submit
									</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div id="fieldmapping-tab" class="tab-pane fade">
					<div class="tab-body-container">
							<div class="head-content hide" style="height: 0px;">
								<h1 class="h1-head" id='field_mapping_id'>Field Mapping</h1>
							</div> 
							
							
							<div  class="form-body-container field-mapping-form"  style="width: 500px; min-height: 61px">
							
							<!-- *********** display none **************** -->
							<ul class="form-container">
								<li id="li_fileformat_txt_id">
									<div class="label-field">
										<label>File Format Type</label>
									</div>
									<div class="input-field">
										<input class="input-text2" type="text"  id="file_formatType_id" placeholder='Please enter file format type'/>
									<ul class="btn-pull-out">
									<li>
									<button style="display:none" class="submit-icon-btn blue-btn preview-btn" onclick="edit_filedmap_action();">
										<i class="fa fa-edit"></i>Edit
									</button>
									</li>
									</ul>
									</div>
									
									
								</li>
								<li id="li_fileformat_selector_id">
									<div class="label-field">
										<label>File Format Type</label>
									</div>
									<div class="input-field" style="padding: 2px; padding-top: 1px solid #e2e2e2;">
										<select class="input-select2" id="fileformat_filedmap_id" onmousedown="filedmap_fileformat_onmouse()" onchange="fileformat_filed_onchange()">
										<option>Please select file format type</option>
										</select>
									<ul class="btn-pull-out">
									<li>
									<button style="display:none" class="submit-icon-btn green-btn preview-btn" onclick="add_filedmap_action();">
										<i class="fa fa-plus"></i>Add
									</button>
									</li>
									<li>
									<button style="display:none" class="submit-icon-btn red-btn preview-btn" onclick="delete_filedmap_action();">
										<i class="fa fa-trash" style="padding-left: 5px;"></i>
									</button>
									</li>
									</ul>
									</div>
								</li>
							</ul>
						<!-- *********** display none **************** -->
							
							</div>
							<div class="table-body-container fieldmap-report-container" id="table-body-container_id">
								<div class="table-inner-container ">
									<table class="tabledata-container" style="width: 100%;table-layout: fixed;"
										id="filedmap_tbl_id">
										<thead>
											<tr>
												<th style="width: 100px;"><h4>S.No</h4></th>
												<th><h4>Column Name</h4></th>
												<th style="width: 200px;"><h4>Data Type</h4></th>
												<th style="width: 200px;"><h4>Date Format</h4></th>
												<th style="width: 200px;"><h4>Enable Mask</h4></th>
												<th style="display:none"><h4>Delete</h4></th>
												<!-- <th><h4>Up/Down</h4></th> -->
											</tr>
										</thead>
										<tbody id="filedmap_table_body_id"></tbody>
									</table>
								</div>
							</div>
							<ul class="btn-container2 field-mapping-btn-container" style="padding-bottom: 0px;">
								<!-- <li>
								    <button class="add-btn pull-left" onclick="addRow(event)" style="margin-right: 10px;">+</button> 
									<button style="cursor: pointer;display:none;" class="add-btn red-btn pull-left" onclick="delete_filedmap_action();" id="del_filed_id"><i class="fa fa-trash"></i></button>
								</li> -->
								<li>
									<button class="submit-icon-btn green-btn"
										onclick="control_act(this)" id="btnAddProfile">
										<i class="fa fa-save"></i>Save
									</button>
								</li>
								<li>
									<button  class="submit-icon-btn red-btn"
										onclick="delete_filedmap_action()" id="del_filed_id">
										<i class="fa fa-trash"></i>Delete
									</button>
								</li>
								
							</ul>
						</div> 
					</div>
					
					
					<div id="fieldname-tab" class="tab-pane fade">
					<div class="tab-body-container">
					<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" >
					<div class="head-content1 bg-gd-blue">
							<h3 class="h3-head">Unique data for field</h3>
						</div>
						<div class="form-body-container unique-filter" style="width: 500px; display: table;">
							<ul class="form-container pull-left " style="width: 410px;">
							<li style="display:none" id="li_custom_fileformat_id">
									<div class="label-field">
										<label>File Format Type</label>
									</div>
									<div class="input-field" style="padding: 2px;">
										<select class="input-select2" id="fileformat_mask_ID" onmousedown= "fileformt_onmouse_custommask()" onchange="fileformat_onchange_customask()">
										<option>Please select file format type</option>
										</select>
									</div>
								</li>
								<li>
									<div class="label-field" style="width:145px;">
										<label>Field Name</label>
									</div>
									<div class="input-field multiselect-field" style="padding: 2px;border-color: #e2e2e2 !important;">
		     						 <div class="multiselect">
							  			<div class="selectBox">
										<select class="input-select2" id="filedname_list_id" >
										</select>								        		
							    		
							    		</div>
							  		
							  		</div>
							  	</div>
													
								</li>
								</ul>

							<div style="padding: 0px 0px 0px 5px;" class="pull-right">
										<button class="submit-icon-btn1 green-btn " onclick="get_fieldname_submit_action()">
										<i class="fa fa-save"></i>Submit
										</button>
									</div>
							
						</div>
						
						<div class="table-body-container snapshot-scroll1">
								<div class="table-inner-container ">
									<table class="tabledata-container copytable" style="width: 100%;" id="filedname_value_id">
										<thead>
											<tr>
												<th style="width: 60px;">
												<h4>Sl.No</h4></th>												
												<th><h4>Field Value</h4></th>
											</tr>
										</thead>
										<tbody >
											
										</tbody>
									</table>
								</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 gradient-border" >
					<div class="head-content1 bg-gd-green">
							<h3 class="h3-head">Snapshot record count</h3>
						</div>
					<div class="table-body-container snapshot-scroll2" >
								<div class="table-inner-container ">
									<table class="tabledata-container green-tablehead copytable" style="width: 100%;" id="maskheader_id">
										<thead>
											<tr>
												<th><h4>Field Name</h4></th>												
												<th><h4>Total Count</h4></th>
												<th><h4>Distinct Count</h4></th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								</div>
						</div>
					</div>
					</div>
					</div>
					</div>
					<div id="audit-subfile-summary-tab" class="tab-pane fade">
					<div class="tab-body-container">
					<div class="filter-form-container">
					<div class="filter-form-inner-container" >
					<ul class="filter-form filter-form-row row" style="position: relative; ">

		<li class="col-lg-2 col-md-2">
				<div class="label-field">
 						<label>From Date</label>
				</div>
				<div class="form-field" >
						<input name="startDate" type='text' id='txtStartDate' class="input-date1"  readonly />
                        <i style="color: #7BC526;" class="fa fa-calendar field-icon datepicker"></i>
				</div>
		</li>
		<li class="col-lg-2 col-md-2" >
				<div class="label-field">
					<label>To Date</label>
				</div>
				<div class="form-field">
					<input name="endDate" type="text" id="txtEndDate" class="input-date1"  readonly />
					<i style="color: #FA0807;" class="fa fa-calendar field-icon datepicker"></i>
				</div>
		</li>
		<li class="col-lg-2 col-md-2">
		<div class="field-group">
<div class="label-field" >
							<label>Status</label>
						</div>
						<div class="form-field" style="width: 100%;">
								<select class="input-select1" id="report_listID">	
								<option value='Success'>Select</option>							
								<option>All</option>
								<option>Success</option>
								<option>Failed</option>  
								</select>
						</div>
						</div></li>
		
		
		
		<li class="col-lg-1 col-md-1 btn-absolute" style="">
		<button class="submit-icon-btn blue-btn " onclick="show_splitreport_action()">
										<i class="fa fa-search"></i>Submit
										</button>
		</li>
</ul>	
<div>

</div>
</div>
</div>
					<div class="table-body-container split-report-container" style="height: 494px!important;">
								<div class="table-inner-container  split-report-inner-container">
									<table class="tabledata-container split-report" style="width: 100%;" id="split_report_tbl_id">
										<thead>
											<tr>
												
												<th><h4>CEMS file name</h4></th>
												<th style="width: 160px;"><h4>No. of rows in CEMS file</h4></th>
												<th><h4>Output file Name</h4></th>
												<th  style="width: 160px;"><h4>No. of rows in Out file</h4></th>
												<th style="width: 230px;"><h4>Processed time</h4></th>
												<th style="width: 230px;"><h4>Status</h4></th>
											</tr>
										</thead>
										<tbody></tbody>		
										</table>
								</div>
						</div>	
					</div>
					</div>
				</div>
				</div>
		
	</section>	
</body>

<script>
//Day Picker
$("#txtStartDate").datetimepicker({
    ignoreReadonly: true,
    format: 'MM/DD/YYYY',
    defaultDate: new Date(),
    maxDate: new Date()
});


$("#txtEndDate").datetimepicker({
    ignoreReadonly: true,
    format: 'MM/DD/YYYY',
    defaultDate: new Date(),
    maxDate: new Date(),
    minDate: new Date()

});

$('#txtStartDate').datetimepicker().on('dp.change', function (e) {
    $("#txtEndDate").data('DateTimePicker').minDate(new Date(e.date));
});
//Day Picker
</script>   
<script>
/* $('.split-report').DataTable(); */
</script>


<script>
	//---------input file customize---------//
	$(document).ready(function() {
		$('#falseinput').click(function() {
			$("#fileinput").click();

		});
	});
	$('#fileinput').change(function() {
		$('#selected_filename').html($('#fileinput')[0].files[0].name);
	});
	//---------input file customize---------//
</script>
		
<script type="text/javascript">
$(document).mouseup(function (e)
{
		var container = $("#mult_checkboxes_id"); // YOUR CONTAINER SELECTOR
		
		if (!container.is(e.target) // if the target of the click isn't the container...
		&& container.has(e.target).length === 0) // ... nor a descendant of the container
		{
		container.hide();
		}
});

$(document).mouseup(function (e)
				{
	var container = $("#checkboxes1"); // YOUR CONTAINER SELECTOR
	
	if (!container.is(e.target) // if the target of the click isn't the container...
	&& container.has(e.target).length === 0) // ... nor a descendant of the container
	{
	container.hide();
	}
});
		


</script>
</html>