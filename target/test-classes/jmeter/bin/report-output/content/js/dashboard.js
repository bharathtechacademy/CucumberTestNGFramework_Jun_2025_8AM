/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 66.66666666666667, "KoPercent": 33.333333333333336};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.6533333333333333, 500, 1500, "[res_key=reportgenerator_summary_total]"], "isController": false}, "titles": ["[res_key=reportgenerator_summary_apdex_apdex]", "[res_key=reportgenerator_summary_apdex_satisfied]", "[res_key=reportgenerator_summary_apdex_tolerated]", "[res_key=reportgenerator_summary_apdex_samplers]"], "items": [{"data": [0.9925, 500, 1500, "2.Get Repositorywith valid User"], "isController": false}, {"data": [0.0, 500, 1500, "1.Create Repository with Invalid Data"], "isController": false}, {"data": [0.9675, 500, 1500, "3.Update Repositorywith valid User"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["[res_key=reportgenerator_summary_total]", 600, 200, 33.333333333333336, 438.6033333333336, 356, 1169, 415.0, 477.0, 523.8999999999999, 992.8800000000001, 50.03335557038025, 293.5603716279603, 17.671286065710472], "isController": false}, "titles": ["[res_key=reportgenerator_summary_statistics_label]", "[res_key=reportgenerator_summary_statistics_count]", "[res_key=reportgenerator_summary_statistics_error_count]", "[res_key=reportgenerator_summary_statistics_error_percent]", "[res_key=reportgenerator_summary_statistics_mean]", "[res_key=reportgenerator_summary_statistics_min]", "[res_key=reportgenerator_summary_statistics_max]", "[res_key=reportgenerator_summary_statistics_median]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_throughput]", "[res_key=reportgenerator_summary_statistics_kbytes]", "[res_key=reportgenerator_summary_statistics_sent_kbytes]"], "items": [{"data": ["2.Get Repositorywith valid User", 200, 0, 0.0, 419.31000000000034, 373, 935, 414.5, 446.0, 457.84999999999997, 596.6700000000003, 19.404288347724847, 153.46868708765888, 5.457456097797613], "isController": false}, {"data": ["1.Create Repository with Invalid Data", 200, 200, 100.0, 458.09999999999997, 356, 1169, 417.0, 508.6, 927.1499999999999, 1165.96, 17.881090746535538, 33.15405677246312, 7.35150312919088], "isController": false}, {"data": ["3.Update Repositorywith valid User", 200, 0, 0.0, 438.40000000000015, 379, 981, 414.0, 475.9000000000001, 522.0, 959.95, 19.40805434255216, 152.13450691411936, 7.126394953905871], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["[res_key=reportgenerator_summary_errors_type]", "[res_key=reportgenerator_summary_errors_count]", "[res_key=reportgenerator_summary_errors_rate_error]", "[res_key=reportgenerator_summary_errors_rate_all]"], "items": [{"data": ["422/Unprocessable Entity", 50, 25.0, 8.333333333333334], "isController": false}, {"data": ["403/Forbidden", 150, 75.0, 25.0], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["[res_key=reportgenerator_top5_total]", 600, 200, "403/Forbidden", 150, "422/Unprocessable Entity", 50, "", "", "", "", "", ""], "isController": false}, "titles": ["[res_key=reportgenerator_top5_label]", "[res_key=reportgenerator_top5_sample_count]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]"], "items": [{"data": [], "isController": false}, {"data": ["1.Create Repository with Invalid Data", 200, 200, "403/Forbidden", 150, "422/Unprocessable Entity", 50, "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
