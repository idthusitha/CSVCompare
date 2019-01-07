<jsp:include flush="true" page="common/header.jsp" />

<script src="js/SQLike.js"></script>

<script>
$(document).ready(function() {
    //$("#CommonSupportForm").validationEngine();
    loadHeading();
    
	$.ajax({
        url: 'CommonSupportAction.do',
        method: 'post',
        data: {
            actionType: "load"            
        },
        dataType: "json",
        success: function(data) { 
         	var dataArray = eval(data);	
         	var html = loadHeading();
   
         	$(jQuery.parseJSON(JSON.stringify(dataArray))).each(function() { 
         		html +="<tr>";
         		html +="<td class=\"tablegridcell\">"+this.ReportLabelMapping_id+"</td>";
         		html +="<td class=\"tablegridcell\">"+this.languageId+"</td>";
         		html +="<td class=\"tablegridcell\">"+this.reportId+"</td>";
         		html +="<td class=\"tablegridcell\">"+this.reportLabelsId+"</td>";
         		html +="<td class=\"tablegridcell\">"+this.labelValue+"</td>";
         		html +="<td class=\"tablegridcell\">"+this.status+"</td>";				  					
         		html +="</tr>";
         	});
         	html +="</table>";
         	$("#data_grid").html(html);
        }
    });
});

function loadHeading(){
	var htmlData =""+ 
	"<table class=\"tableheadlabel tablegrid\" cellSpacing=0 cellPadding=1 width=\"100%\" border=1>\n" +
	"	<tr class=\"tableheadbg tablegridcell\" align=\"center\">\n" +
	"		<td class=\"tablegridcell\" align=\"center\">Report Label Mapping id</td>\n" +
	"		<td class=\"tablegridcell\" align=\"center\">language Id</td>\n" +
	"		<td class=\"tablegridcell\" align=\"center\">report Id</td>\n" +
	"		<td class=\"tablegridcell\" align=\"center\">report Labels Id</td>\n" +
	"		<td class=\"tablegridcell\" align=\"center\">label Value</td>\n" +
	"		<td class=\"tablegridcell\" align=\"center\">status</td>\n" +	
	"	</tr>\n";
	$("#data_grid").html(htmlData);
	return htmlData;
}


</script>

<div id="sub-header"> </div>
<br /><br />
<br />

<div id="devcontent">
    <fieldset>
        <legend>Report Label Mapping</legend>
        <div id="data_grid"></div>
        
    </fieldset>
</div>
<br /><br /><br />

<script>
$(document).ready(function() {
	
});
</script>

<jsp:include flush="true" page="common/footer.jsp" />



