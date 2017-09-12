<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet" href="${path}/css/admin-trend.css" />

<script src="${path}/js/laydate/laydate.js"></script>
<link href="${path}/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path}/js/myPagination/js/myPagination/page.css" />

<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<script src="${path}/js/tool.js"></script>
</head>
<body>
<script type="text/javascript">
		$(function(){
			var index=0;
			$(".tabTit li").click(function(){
				index=$(".tabTit li").index(this);
				$(this).addClass("cur").siblings().removeClass("cur");
				
				$(".tab-content .contentDiv").eq(index).show().siblings().hide();
				
				if(0==index){
					$("#positionSpan").hide();
					$("#type").show();
					setTypeOptions();
				}else{
					$("#positionSpan").show();
					$("#type").hide();
					showPosData();
				}
			})
		})
	
		//通用表格隔行背景
		$(function(){
			$(".tableList tbody").each(function(){
				$(this).find("tr:even").addClass("even");
				$(this).find("tr:odd").addClass("odd");
			})
		})
	
	
	</script>

	<div class="sdk-content">
		<ul class="breadcrumb">
			<li>您当前的位置：</li>
			<tags:breadcrumb />
			<li>统计图</li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				   <div class="domainDiv">
						<label class="control-label" for="" style="font-size: 16px;float:left;line-height:2;">域名：</label>
						<select id="domain" class="form-control input-sm" onchange="domainChange()"
							style="display: initial; width: inherit;float:left;">
							<c:forEach items="${domains}" var="item">
								<option value="${item.tblId}">${item.positionName}</option>
							</c:forEach>
						</select>
						<button type="button" id="search" class="btn btn-primary btn-sm"
							onclick="search();">
							<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
						</button>
					</div>
					<div class="panel-body">
						<div class="admin-content">
								<!--分类浏览-->
								<div class="section-box">
									<div class="titleDiv">
									        <div style="display: inline;width:500px;text-align: center;"></div>
										    <div class="select-fr">
											    <button type="button" id="exportToExcel1"
														class="btn btn-primary btn-sm" onclick="exportExcel();">
														<i class="icon-search icon-white"></i>导出为Excel
											    </button>
												<span class="laydateBox"> &nbsp;&nbsp;日期：<input
													value="选择日期" class="laydate-icon" id="currentDate" readonly>
												</span>
												<span id="positionSpan" style="display:none;">&nbsp;&nbsp;位置：
											    		<input type="text" id="positionNames" style="height: 25px;line-height:normal;" name="positionNames" onclick="onShowPositionTree()" readOnly/>
											    		<input
															type="hidden" id="positionIds"
															name="positionIds">
														<div id="positionContent" 
															style="display: none;width:304px;height:160px;border: 1px solid #ccc;margin-left: 134px;position: absolute; z-index: 10;background-color: white;">
															<div style="width:304px;height:123px; overflow:auto;">
																<ul id="treePosition" class="ztree"
																	style="margin-top: 0;width: 302px;background-color: white;"></ul>
															</div>
															<button class="btn btn-primary btn-sm" style="float:right;margin-right:3px;height:30px;" onclick="searchByPos();">确定</button>
														</div>
												</span>
											    <!--选择类别-->
												<span id="type">
												&nbsp;&nbsp;类别 : 
		                                            <select id="infoType" onchange="showInfoData();" class="select">
		                                                 <option value="">请选择..</option>
		                                            </select>
	                                            </span> 
												<script>
													var dateChange = function(){
														if($("#type").is(":hidden")){
															search();//查询
														}else{
															setTypeOptions();//资讯页面，类型跟着时间变化
														}
													}
													setDateConfig("currentDate",dateChange);
													
													function onShowPositionTree(){
												        var compamyinput =$("#position") ;
												        var positioninputOffset=$("#position").offset();
												        $("#positionContent").slideDown("fast");
												        $("body").bind("mousedown", onPositionBodyDown);
												    }
													
													function hidePositionMenu() {
												        $("#positionContent").fadeOut("fast");
												        $("body").unbind("mousedown", onPositionBodyDown);
												    }
										
	
												    function onPositionBodyDown(event) {
												        if (!(event.target.id == "position"  || event.target.id == "positionContent" ||$(event.target).parents("#positionContent").length>0)) {
												            hidePositionMenu();
												        }
												    }
												    
												    function onPositionClick() {
												    	
												        var zTree = $.fn.zTree.getZTreeObj("treePosition") ;
												        var n="" ,v="";
												        var nodes = zTree.getCheckedNodes(true);
												        nodes.sort(function compare(a,b){return a.id-b.id;});
												        for (var i=0, l=nodes.length; i<l ; i++) {
												            if(nodes[i].isParent==false){
												                n += nodes[i].name + ",";
												                v += nodes[i].id+",";
												            }
												        }
												        if (n.length > 0 )  { n = n.substring(0, n.length-1) , v=v.substring(0, v.length-1) }
												        var position = $("#positionNames");
												        var positionValue=$("#positionIds");
												     	position.val(n);
												     	positionValue.val(v);
												    }
												    
												    var positionSetting = {
												            check: {
												                enable: true ,
												                chkStyle: "checkbox"
												            },
												            data: {
												                simpleData: {
												                    enable: true
												                }
												            } ,
												            callback: {
												            	onCheck: onPositionClick,
												            }
												        };
												    
												    $().ready(function(){
												        setPositions();
												    });
												</script>
										</div>
										<h2>分类浏览</h2>
										<ul class="tabTit titslt">
											<li class="cur">资讯</li>
											<li>位置</li>
										</ul>
									</div>

									<div class="whiteDiv tab-content">
										<div class="contentDiv">
											<div class="textTop">
												<dl class="fr text-zj">
													<dt>总计：</dt>
													<dd class="bg-red">点击次数：<span id="infoClickCnt">0</span>次</dd>
													<dd class="bg-green" >停留时长：<span id="infoStayCnt">0</span></dd>
													<dd class="bg-blue">IP数：<span id="infoIPCnt">0</span></dd>
												</dl>
												<div class="fl">
													今日更新资讯<b class="red" id="infoCnt">0</b>条
												</div>
											</div>
											<div>
												<ul id="sortable" class="ui-widget-content sortable">
													<li class="ui-state-default">
														<div class="ui-widget-content resize resizePanel">
															<div class="sortHandle">列表</div>
															<div class="tablePanel">
																<table class="tableList">
																	<colgroup>
																		<col width="90" />
																		<col width="" />
																		<col width="90" />
																		<col width="110" />
																		<col width="90" />
																		<col width="90" />
																		<col width="90" />
																	</colgroup>
																	<tbody id="infoDetail">
																	</tbody>
																</table>
															</div>
															<div id="infoPage"></div>
														</div>
													</li>
													<li class="ui-state-default">
			 											<div  class="ui-widget-content resize" style="height:300px;">
			 												<div class="sortHandle">趋势图</div>
															<div id="timeTrendLine" class="trendline"></div>
														</div>
													</li>
												</ul>
											</div>
										</div>
										<div class="contentDiv" style="display: none;">
											<div class="textTop" >
												<div style="float: right;">
													<dl class="fl text-zj">
														<dt>总计：</dt>
														<dd class="bg-red">点击次数：<span id="posClickCnt">0</span>次</dd>
														<dd class="bg-green">停留时长：<span id="posStayCnt">0</span></dd>
														<dd class="bg-blue">IP数：<span id="posIPCnt">0</span></dd>
													</dl>
												</div>
											</div>
											<div>
												<ul id="sortable1" class="ui-widget-content sortable">
													<li class="ui-state-default">
														<div class="ui-widget-content resize resizePanel">
															<div class="sortHandle">列表</div>
															<div class="tablePanel">
																<table class="tableList">
																	<tbody id="posDetail">
																		<tr>
																			<td>位置</td>
																			<td>点击次数</td>
																			<td>停留时长</td>
																			<td>IP数</td>
																			<td>更新数量</td>
																			<td width="80">更新详情</td>
																		</tr>
																		<tr>
																			<td>1</td>
																			<td>2</td>
																			<td>100</td>
																			<td>10s</td>
																			<td>10</td>
																			<td><a class="xiangqing2" name="xiangqing2"></a></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div id="posPage">
														</div>
													</li>
													<li class="ui-state-default">
			 											<div  class="ui-widget-content resize" style="height:300px;">
			 												<div class="sortHandle">趋势图</div>
															<div id="positionTrendLine" class="trendline"></div>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>
								<!--分类浏览end-->

								<!--分类浏览资讯变化详情-->
								<div class="xq-box">
									<table class="xq-table">
										<caption>&nbsp;&nbsp;&nbsp;同一篇资讯在不同位置表现</caption>
										<tbody id="infoPosDetail" style="overflow:auto;">
											<tr>
												<td width="20%">位置</td>
												<td width="20%">时长</td>
												<td width="20%">点击次数</td>
												<td width="20%">停留时长</td>
												<td width="20%">IP数</td>
											</tr>
											<tr>
												<td>1</td>
												<td>1h</td>
												<td>10</td>
												<td>30s</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--分类浏览资讯变化详情end-->
								<!--分类浏览位置变化详情-->
								<div class="xq-box2">
									<table class="xq-table pst">
										<caption>&nbsp;&nbsp;&nbsp;同一位置在不同资讯表现</caption>
										<tbody id="posInfoDetail" style="overflow:auto;">
											<tr>
												<td width="100">发布时间</td>
												<td width="180">标题</td>
												<td width="95">点击次数</td>
												<td width="95">停留时长</td>
												<td width="95">IP数</td>
											</tr>
											<tr>
												<td>2016-07-25<br /> 12:26:54
												</td>
												<td>标题 [意甲 1] 尤文图斯 VS 托特纳姆热刺 [英超 3]</td>
												<td>10</td>
												<td>30s</td>
												<td>5</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--分类浏览位置变化详情end-->
							</div>
					</div>
				</div>
		</div>

		<script src="${path}/js/echart/dist/echarts.js"></script>
		<script type="text/javascript">
			$(function(){
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( "#sortable1" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 150,minWidth: 150});
				$(window).resize(
						function(){
							chartResize(timeTrendLine);
							chartResize(positionInfoCountBar);
					}
				);
			});
		
	   		var pageSize = 10;
	   		
	   		var domainChange = function(){
	   			setTypeOptions();//
	   			setPositions();//获取位置信息
	   		}
	   		
	   		//通过位置查询
	   		var searchByPos = function(){
	   			showPosData();//查询显示位置信息
	   			hidePositionMenu();
	   		}
	   		 
	    	//设置类别选择数据
	    	var setTypeOptions = function(){
		    	$.post("${path}/category/getInfoType.do",{
					date:$('#currentDate').val(),
					domain:$('#domain').val()
				},function(data){
					var json = JSON.parse(data).infoTypes;
					var optionstring = ""; 
					if(null != json && undefined != json && ""!=json){
						for(var i=0;i < json.length;i++){
	                        optionstring += "<option value=\"" + json[i].tblId + "\" >" + json[i].typeName + "</option>";  
						}
					}
					$("#infoType").html(optionstring);
					showInfoData();
				});
	    	}
	    	
	    	var setPositions = function(){
		    		$.ajax({
			            url: "${path}/category/getPositions.do?domainId=" + $('#domain').val(),
			            dataType:"json" ,
			            success: function(data){
			                $.fn.zTree.init($("#treePosition"), positionSetting, data.positions);
			                $("#positionIds").val(data.positionIds);
			                $("#positionNames").val(data.positionNames);
			                if($("#type").is(":hidden")){//查询位置
			                	showPosData();//查询显示位置信息
			                }
			            }
			        });
	    	}
	    	
			var showInfoPosTblCl = function(){
				$("#infoPosDetail").append("<td width=\"25%\">位置</td><td width=\"25%\">点击次数</td><td width=\"25%\">停留时长</td><td width=\"25%\">IP数</td>");
			}
			var addInfoPosTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>positionName</td><td>clickCnt</td><td>stayCnts</td><td>ipCnt</td></tr>"
					ele = ele.replace("positionName",data.positionName).replace("stayCnt",data.stayCnt).replace("clickCnt", data.clickCnt).replace("ipCnt",data.ipCnt);
					$("#infoPosDetail").append(ele);
				}
			}
			var showInfoPosDetail = function(infoId){
				$("#infoPosDetail").empty();
				showInfoPosTblCl();
				$.post("${path}/category/findPositionByInfo.do",{
					date:$('#currentDate').val(),
					domain:$('#domain').val(),
					infoId:infoId
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						var infoPosData = json.positionByInfos;
						if(null == infoPosData || undefined == infoPosData || 0 >= infoPosData.length){
							$("#infoPosDetail").append("<tr><td colspan=\"4\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoPosData.length;i++){
								var ele = {
										positionName:infoPosData[i].positionName,
										clickCnt:infoPosData[i].clickCnt,
										stayCnt:infoPosData[i].stayCnt,
										ipCnt:infoPosData[i].ipCnt
								}
								addInfoPosTbRow(ele);
							}
						}
						
					}else{
						$("#infoDetail").append("<tr><td colspan=\"7\">没有数据</td></tr>");
					}
				});
			}
			
			//获取资讯信息总计
			var getInfoSummary = function(){
				$.post("${path}/category/infoPerDayCount.do",{
					date:$('#currentDate').val(),
					infoType:$('#infoType').val(),
					domain:$('#domain').val()
				},function(data){
					data = JSON.parse(data);
					if(null != data && undefined != data&& "" != data){
						$("#infoCnt").text(data.infoCnt);	
						$("#infoClickCnt").text(data.clickCnt);
						$("#infoStayCnt").text(formatSeconds1(data.stayCnt));
						$("#infoIPCnt").text(data.ipCnt);
					}else{
						$("#infoCnt").text("0");	
						$("#infoClickCnt").text("0");
						$("#infoStayCnt").text("0");
						$("#infoIPCnt").text("0");
					}
				});
			}
			
			var showInfoTbCl = function(){
				$("#infoDetail").append("<tr><td>类别</td><td style=\"display:none;\">资讯ID</td><td>标题</td><td>发布时间</td><td>点击次数</td><td>停留时长</td><td>IP数</td><td>变化详情</td></tr>");
			}
			
			var onInfoMouseOver = function(ele){
				$(".xq-box").css('left', $(ele).offset().left-700).css('top', $(ele).offset().top-55).show();
				var parentObj = $(ele).parent().parent();
				var infoIdEle = $(parentObj).children(".Id")[0];
				showInfoPosDetail($(infoIdEle).text());
			}	
			var onInfoMouseOut = function(ele){
				$(".xq-box").hide();
			}	
			var addInfoTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>infoTypeName</td><td style=\"display:none;\" class=\"Id\">infoId</td><td>infoName</td><td class=\"time\">createDate</td><td>clickCnt</td><td>stayCnts</td><td>ipCnt</td><td><a class=\"xiangqing\" name=\"xiangqing\" onmouseover=\"onInfoMouseOver(this)\" onmouseout=\"onInfoMouseOut(this)\"></a></td></tr>";
					ele = ele.replace("infoId",data.infoId).replace("infoTypeName",data.infoTypeName).replace("infoName",data.infoName).replace("createDate",data.createDate).replace("stayCnt",data.stayCnt).replace("clickCnt", data.clickCnt).replace("ipCnt",data.ipCnt);
					$("#infoDetail").append(ele);
				}
			}
			//显示资讯列表
			var showInfoDetail = function(pageNumber,pageSize){
				$("#infoDetail").empty();
				showInfoTbCl();
				$.post("${path}/category/findInfoStatD.do",{
					date:$('#currentDate').val(),
					infoType:$('#infoType').val(),
					domain:$('#domain').val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#infoPage").myPagination({
					          currPage: pageNumber,
					          pageCount: json.infoStatDShowers.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showInfoDetail(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.infoStatDShowers.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#infoDetail").append("<tr><td colspan=\"7\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										infoId:infoData[i].infoId,
										infoTypeName:infoData[i].infoTypeName,
										infoName:infoData[i].infoName,
										createDate:infoData[i].createDate,
										clickCnt:infoData[i].clickCnt,
										stayCnt:infoData[i].stayCnt,
										ipCnt:infoData[i].ipCnt
								}
								addInfoTbRow(ele);
							}
						}
						
					}else{
						$("#infoDetail").append("<tr><td colspan=\"7\">没有数据</td></tr>");
					}
				});
			}
			//显示资讯信息
			var showInfoData = function(){
				getInfoSummary();
				showInfoDetail(1,pageSize);
				reLoadInfoTrendLine(echartsCopy);
			}
			
			//显示位置资讯信息详情列表表头
			var showPosInfoTblCl = function(){
				$("#posInfoDetail").append("<td width=\"100\">更新时间</td><td width=\"180\">标题</td><td width=\"95\">点击次数</td><td width=\"95\">停留时长</td><td width=\"95\">IP数</td>");
			}
			
			
			//添加位置资讯信息详情列表行
			var addPosInfoTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>updateDate</td><td>infoName</td><td>clickCnt</td><td>stayCnts</td><td>ipCnt</td></tr>"
					ele = ele.replace("updateDate",data.updateDate).replace("infoName",data.infoName).replace("stayCnt",data.stayCnt).replace("clickCnt", data.clickCnt).replace("ipCnt",data.ipCnt);
					$("#posInfoDetail").append(ele);
				}
			}
			//显示位置资讯信息
			var showPosInfoDetail = function(posId){
				$("#posInfoDetail").empty();
				showPosInfoTblCl();
				$.post("${path}/category/findInfoByPosition.do",{
					date:$('#currentDate').val(),
					domain:$('#domain').val(),
					positionId:posId
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						var posInfoData = json.infoByPositions;
						if(null == posInfoData || undefined == posInfoData || 0 >= posInfoData.length){
							$("#posInfoDetail").append("<tr><td colspan=\"5\">没有数据</td></tr>");
						}else{
							for(var i=0;i < posInfoData.length;i++){
								var ele = {
										updateDate:posInfoData[i].updateDate,
										infoName:posInfoData[i].infoName,
										clickCnt:posInfoData[i].clickCnt,
										stayCnt:posInfoData[i].stayCnt,
										ipCnt:posInfoData[i].ipCnt
								}
								addPosInfoTbRow(ele);
							}
						}
						
					}else{
						$("#posInfoDetail").append("<tr><td colspan=\"5\">没有数据</td></tr>");
					}
				});
			}
			
			//获取位置信息总计
			var getPosSummary = function(){
				$.post("${path}/category/positionPerDayCount.do",{
					date:$("#currentDate").val(),
					positionIds:$("#positionIds").val(),
					domain:$("#domain").val()
				},function(data){
					data = JSON.parse(data).positionCountByDay;
					if(null != data && undefined != data&& "" != data){
						$("#posClickCnt").text(data.clickCntTotal);
						$("#posStayCnt").text(formatSeconds1(data.stayCntTotal));
						$("#posIPCnt").text(data.ipCntTotal);
					}else{
						$("#posClickCnt").text("0");
						$("#posStayCnt").text("0");
						$("#posIPCnt").text("0");
					}
				});
			}
			
			//显示位置列表表头
			var showPosTbCl = function(){
				$("#posDetail").append("<tr><td>位置</td><td style=\"display:none\">位置id</td><td>点击次数</td><td>停留时长</td><td>IP数</td><td>更新数量</td><td width=\"80\">更新详情</td></tr>");
			}
			var onPosMouseOver = function(ele){
				$(".xq-box2").css('left', $(ele).offset().left-900).css('top', $(ele).offset().top - 60 ).show();
				var parentObj = $(ele).parent().parent();
				var infoIdEle = $(parentObj).children(".Id")[0];
				showPosInfoDetail($(infoIdEle).text());
			}	
			var onPosMouseOut = function(ele){
				$(".xq-box2").hide();
			}	
			//添加位置列表行
			var addPosTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>positionName</td><td style=\"display:none;\" class=\"Id\">positionId</td><td>clickCnt</td><td>stayCnts</td><td>ipCnt</td><td>infoCnt</td><td><a class=\"xiangqing2\" name=\"xiangqing2\" onmouseover=\"onPosMouseOver(this)\" onmouseout=\"onPosMouseOut(this)\"></a></td></tr>";
					ele = ele.replace("positionId",data.positionId).replace("positionName",data.positionName).replace("stayCnt",data.stayCnt).replace("clickCnt", data.clickCnt).replace("ipCnt",data.ipCnt).replace("infoCnt",data.infoCnt);
					$("#posDetail").append(ele);
				}
			}
			//显示位置列表
			var showPosDetail = function(pageNumber,pageSize){
				$("#posDetail").empty();
				showPosTbCl();
				$.post("${path}/category/findPositionStatD.do",{
					date:$("#currentDate").val(),
					positionIds:$("#positionIds").val(),
					domain:$("#domain").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#posPage").myPagination({
					          currPage: pageNumber,
					          pageCount: json.positionStatDs.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showPosDetail(page,pageSize);
				                    }  
				        		}
					        });
						var postData = json.positionStatDs.list;
						if(null == postData || undefined == postData || 0 >= postData.length){
							$("#posDetail").append("<tr><td colspan=\"7\">没有数据</td></tr>");
						}else{
							for(var i=0;i < postData.length;i++){
								var ele = {
										positionId:postData[i].positionId,
										positionName:postData[i].positionName,
										clickCnt:postData[i].clickCnt,
										stayCnt:postData[i].stayCnt,
										ipCnt:postData[i].ipcnt,
										infoCnt:postData[i].infoCnt
								}
								addPosTbRow(ele);
							}
						}
						
					}else{
						$("#posDetail").append("<tr><td colspan=\"7\">没有数据</td></tr>");
					}
				});
			}
			
			//显示资讯信息
			var showPosData = function(){
				getPosSummary();
				showPosDetail(1,pageSize);
				reLoadPositionTrendLine(echartsCopy);
			}
			
			
			var search = function(){
				//类型标签隐藏，表示当前页是位置标签页
				if($("#type").is(":hidden")){
					showPosData();//查询显示位置信息
				}else{
					showInfoData();//查询显示资讯信息
				}
			}
			
		
		
			var echartsCopy;
			
			function exportExcel(){
				//类型标签隐藏，表示当前页是位置标签页
				if($("#type").is(":hidden")){
					exportPosition();//导出位置信息
				}else{
					exportInfo();//导出资讯信息
				}
			}
			
			function exportInfo(){
				var domain = $("#domain").val();
				var infoType =$("#infoType").val();
				var date = $("#currentDate").val();
				window.location = "${path}/category/exportInfoList.do?date="+date+"&domain="+domain+"&infoType="+infoType;
			}
			
			function exportPosition(){
				var domain = $("#domain").val();
				var positionIds =$("#positionIds").val();
				var date = $("#currentDate").val();
				window.location = "${path}/category/exportPositionList.do?date="+date+"&domain="+domain+"&positionIds="+positionIds;
			}
			
			var timeTrendLine;
			function  reLoadInfoTrendLine(echarts){
				var domain = $("#domain").val();
				var infoType =$("#infoType").val();
				var date = $("#currentDate").val();
				
				$.ajax({ 
					url: "${path}/category/temporalDisByInfoType.do",
					type:"POST",
					data : {
						infoType: infoType,
						date : date,
						domain: domain
					},
					success : function(data) {

						var json = JSON.parse(data);
						
						timeTrendLine = echarts.init(document
								.getElementById('timeTrendLine'));
						timeTrendLine.setOption({
							title : {
								x : '20',
								text : '资讯时间分布',
								textStyle : {
									fontSize : '16',
									fontWeight : 'bold',
								}
							},
							tooltip : {
								trigger : 'axis'
							},
							color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
									'#ffb980', '#d87a80' ],
							legend : {
								show : true,
								orient : 'horizontal',
								data : [ {
									name : '点击次数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : 'IP数量',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : '停留时长（秒）',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}

								]

							},
							grid : {
								borderWidth : 0,
								y : 50,
								y2 : 30,
								x : 20,
								x2 : 20
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : json.scales,
								splitLine : {
									show : true,
									lineStyle : {
										color : '#f5f5f5',
										width : 1,
										type : 'solid'
									}
								},
								axisTick : {
									show : true,
									lineStyle : {
										color : '#ccc',
										width : 1,
										type : 'solid'
									}
								},
								axisLine : {
									lineStyle : {
										color : '#ccc',
										width : 1,
										type : 'solid'
									}
								},
								axisLabel : {
									textStyle : {
										color : "#999"
									}
								}
							} ],
							yAxis : [ {
								type : 'value',
								axisLine : {
									show : false
								},
								axisTick : {
									show : false
								},
								axisLabel : {
									show : false
								},
								splitArea : {
									show : false
								},
								splitLine : {
									show : true,
									lineStyle : {
										color : '#f5f5f5',
										width : 1,
										type : 'solid'
									}
								}
							} ],

							series : [
									{
										name : '点击次数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data :json.clickList,
									},
									{
										name : 'IP数量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.ipList,
									},
									{
										name : '停留时长（秒）',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.stayTimeList,
									},

							]
						});
						
					}
				});
			}
			
			var positionTrendLine;
			function  reLoadPositionTrendLine(echarts){
				var domain = $("#domain").val();
				var positionIds =$("#positionIds").val();
				var date = $("#currentDate").val();
				$.ajax({ 
					url: "${path}/category/temporalDisByPosition.do",
					type:"POST",
					data : {
						positionIds:positionIds,
						date : date,
						domain: domain
					},
					success : function(data) {
						var json = JSON.parse(data);
						positionTrendLine = echarts.init(document
								.getElementById('positionTrendLine'));
						positionTrendLine.setOption({
							title : {
								x : '20',
								text : '位置时间分布',
								textStyle : {
									fontSize : '16',
									fontWeight : 'bold',
								}
							},
							tooltip : {
								trigger : 'axis'
							},
							color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
									'#ffb980', '#d87a80' ],
							legend : {
								show : true,
								orient : 'horizontal',
								data : [ {
									name : '点击次数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}, {
									name : 'IP数量',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}, {
									name : '停留时长（秒）',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}

								]
							},
							grid : {
								borderWidth : 0,
								y : 50,
								y2 : 30,
								x : 20,
								x2 : 20
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : json.scales,
								splitLine : {
									show : true,
									lineStyle : {
										color : '#f5f5f5',
										width : 1,
										type : 'solid'
									}
								},
								axisTick : {
									show : true,
									lineStyle : {
										color : '#ccc',
										width : 1,
										type : 'solid'
									}
								},
								axisLine : {
									lineStyle : {
										color : '#ccc',
										width : 1,
										type : 'solid'
									}
								},
								axisLabel : {
									textStyle : {
										color : "#999"
									}
								}
							} ],
							yAxis : [ {
								type : 'value',
								axisLine : {
									show : false
								},
								axisTick : {
									show : false
								},
								axisLabel : {
									show : false
								},
								splitArea : {
									show : false
								},
								splitLine : {
									show : true,
									lineStyle : {
										color : '#f5f5f5',
										width : 1,
										type : 'solid'
									}
								}
							} ],

							series : [
									{
										name : '点击次数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.clickList
									},
									{
										name : 'IP数量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.ipList
									},
									{
										name : '停留时长（秒）',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.stayTimeList
									},

							]
						});
						//分类浏览位置折线图结束	
					}
				});
			}
    
		    // 路径配置
		    require.config({
		       paths: {
		       	 echarts: '${path}/js/echart/dist'
				}
			});

			// 使用
			require([ 'echarts','echarts/chart/line' ],
				function(ec) {
					echartsCopy = ec;
					setTypeOptions();
			});
		</script>
</body>
</html>
