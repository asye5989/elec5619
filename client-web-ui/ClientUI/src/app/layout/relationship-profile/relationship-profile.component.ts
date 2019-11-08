import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";
import { UserService } from "src/app/shared";

@Component({
  selector: "app-relationship-profile",
  templateUrl: "./relationship-profile.component.html",
  styleUrls: ["./relationship-profile.component.scss"],
  animations: [routerTransition()]
})
export class RelationshipProfileComponent implements OnInit {
  score: number = -1;
  show: boolean = false;
  comment: string = "";

  public lineChartLegend: boolean = false;
  public lineChartType: string = "line";
  // lineChart
  public lineChartData: Array<any> = [
    { data: [40], label: "Relationship Score" }
  ];
  public lineChartLabels: Array<any> = ["TEST1"];
  public lineChartOptions: any = {
    responsive: false
  };
  public lineChartColors: Array<any> = [
    {
      // grey
      backgroundColor: "rgba(148,159,177,0.2)",
      borderColor: "rgba(148,159,177,1)",
      pointBackgroundColor: "rgba(148,159,177,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(148,159,177,0.8)"
    },
    {
      // dark grey
      backgroundColor: "rgba(77,83,96,0.2)",
      borderColor: "rgba(77,83,96,1)",
      pointBackgroundColor: "rgba(77,83,96,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(77,83,96,1)"
    },
    {
      // grey
      backgroundColor: "rgba(148,159,177,0.2)",
      borderColor: "rgba(148,159,177,1)",
      pointBackgroundColor: "rgba(148,159,177,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(148,159,177,0.8)"
    }
  ];

  // events
  public chartClicked(e: any): void {
    // console.log(e);
  }

  public chartHovered(e: any): void {
    // console.log(e);
  }

  constructor(private userService: UserService) {}

  ngOnInit() {
    console.log("initialized relationsjhip component");
    this.refreshScore();
  }

  refreshScore() {
    this.score = this.userService.getRelationshipScore();

    if (this.score >= 0) {
      this.show = true;
      switch (this.score) {
        case 20:
          this.comment = "Not Compatible. High risk of issues and conflicts";
          break;
        case 40:
          this.comment =
            "Relationship can work, but not ideal, Very Low chances of success";
          break;
        case 60:
          this.comment = "Relationship can work, Low chances of success";
          break;
        case 80:
          this.comment = "Good Match , High chances of success";
          break;
        default:
          this.comment = "Ideal Match, Very High chances of success ";
          break;
      }
    }
  }
}
