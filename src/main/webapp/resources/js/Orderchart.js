var ctx = document.getElementById("OrderChart");
    var val = [document.getElementById("accepted").value, document.getElementById("pending").value, 0];
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Paid", "Pending", "Cancelled"],
            datasets: [{
                label: 'Orders',
                data: val,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {

                        stepSize:1,
                        beginAtZero:true
                    }
                }]
            }
        }
    });

