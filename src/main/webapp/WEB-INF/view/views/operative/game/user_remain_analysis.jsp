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
<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"> </script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
</head>
<body>
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
					<div class="panel-body">
						<div class="admin-content">
								<div class="section-box">
									<div class="titleDiv">
										   	<input type="hidden" id="platformId" value="${platformId}">
										    <div class="select-fr" style="padding-right: 30px;float:left;margin-left:8px;">
												<span class="laydateBox"> 日期：<input value="选择日期"
													class="laydate-icon" id="dateStart"> 至 <input
													class="laydate-icon" id="dateEnd" value="选择日期">
												</span>
												<span id="type">
												&nbsp;&nbsp;
		                                            <select id="dateType" onchange="dateTypeChange();" style="width:60px;" class="select">
		                                                 <option value="1">近7日</option>
		                                                 <option value="2">近14日</option>
		                                                 <option value="3">近30日</option>
		                                                 <option value="4">近90日</option>
		                                                 <option value="0">自定义</option>
		                                            </select>
	                                            </span> 
										</div>
									</div>

									<div class="whiteDiv tab-content">
										<ul id="sortable" class="ui-widget-content sortable">
											<li class="ui-state-default">
												<div class="ui-widget-content resize resizePanel">
													<div class="sortHandle">列表</div>
													<div class="tablePanel">
														<table class="tableList">
															<tbody id="data">
															</tbody>
														</table>
													</div>
													<div id="page"></div>
												</div>
											</li>
										</ul>
								</div>
							</div>
					</div>
				</div>
		</div>
		<script type="text/javascript">
			var dateChange = function(){
				$("#dateType").val(0);
				search();
			}
			var dateValue = setDateRangeConfig("dateStart","dateEnd",dateChange);
			var dateTypeChange = function(){
				setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
			}	
		
			$(function(){
				setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 300,minWidth: 300});
			});
		
	   		var pageSize = 10;
			var showTbCl = function(){
				$("#data").append("<tr><td rowspan=\"2\">日期</td><td rowspan=\"2\">新增玩家</td><td colspan=\"9\">第N天后留存玩家数（率）</td></tr><tr><td>1天后</td><td>2天后</td><td>3天后</td><td>4天后</td><td>5天后</td><td>6天后</td><td>7天后</td><td>14天后</td><td>30天后</td></tr>");
			}
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class=\"date\">statDate</td><td>registerCount</td><td>one（onPercent）</td><td>two（toPercent）</td><td>three（thrPercent）</td><td>four（foPercent）</td><td>five（fiPercent）</td><td>six（siPercent）</td><td>seven（sePercent）</td><td>fourteen（ftPercent）</td><td>thirty（thyPercent）</td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("registerCount",data.registerCount).replace("one",data.one).replace("onPercent",data.onePercent).replace("two",data.two).replace("toPercent",data.twoPercent).replace("three",data.three).replace("thrPercent",data.threePercent).replace("four",data.four).replace("foPercent",data.fourPercent).replace("five",data.five).replace("fiPercent",data.fivePercent).replace("six",data.six).replace("siPercent",data.sixPercent).replace("seven",data.seven).replace("sePercent",data.sevenPercent).replace("fourteen",data.fourteen).replace("ftPercent",data.fourteenPercent).replace("thirty",data.thirty).replace("thyPercent",data.thirtyPercent);
					$("#data").append(ele);
				}
			}
			
			//保留两位小数
			var getTwoFixedDouble = function(value){
				return (value*100).toFixed(2);
			}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				showTbCl();
				$.post("${path}/game/userremainals/list.do",{
					startDate:$("#dateStart").val(),
					endDate:$("#dateEnd").val(),
					platformId:$("#platformId").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#page").myPagination({
					          currPage: pageNumber,
					          pageCount: json.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										statDate:infoData[i].statDate,
										registerCount:infoData[i].registerCount,
										one:infoData[i].one,
										onePercent:getTwoFixedDouble(infoData[i].onePercent) +"%",
										two:infoData[i].two,
										twoPercent:getTwoFixedDouble(infoData[i].twoPercent) +"%",
										three:infoData[i].three,
										threePercent:getTwoFixedDouble(infoData[i].threePercent) +"%",
										four:infoData[i].four,
										fourPercent:getTwoFixedDouble(infoData[i].fourPercent) +"%",
										five:infoData[i].five,
										fivePercent:getTwoFixedDouble(infoData[i].fivePercent) +"%",
										six:infoData[i].six,
										sixPercent:getTwoFixedDouble(infoData[i].sixPercent) +"%",
										seven:infoData[i].seven,
										sevenPercent:getTwoFixedDouble(infoData[i].sevenPercent) +"%",
										fourteen:infoData[i].fourteen,
										fourteenPercent:getTwoFixedDouble(infoData[i].fourteenPercent) +"%",
										thirty:infoData[i].thirty,
										thirtyPercent:getTwoFixedDouble(infoData[i].thirtyPercent) +"%"
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
					}
				});
			}

			//查询显示
			var search = function(){
				showData(1,pageSize);
			}
		</script>
</body>
</html>
