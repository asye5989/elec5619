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

  lineChartData: Array<any>;
  lineChartLabels: Array<any>;

  lineChartLegend: boolean = false;
  lineChartType: string = "line";
  // lineChart

  lineChartOptions: any = {
    responsive: false
  };
  lineChartColors: Array<any> = [
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

  constructor(private userService: UserService) {}

  ngOnInit() {
    console.log("initialized relationsjhip component");
    this.updateRelationshipStats();
  }

  private updateRelationshipStats() {
    let x: [any[], any[]] = this.userService.getRelationshipStats();

    if (x[0] && x[1] && x[0].length == x[1].length) {
      this.score = x[1][x[1].length - 1]; //latest score is in end
      this.lineChartData = [{ data: x[1], label: "Relationship Score" }];
      this.lineChartLabels = x[0];
    } else {
      this.score = 1;
      this.comment =
        "Score not available. Please ensure that you and your partner have taken persoanlity assesment test and then try back after some time.";
    }

    this.score;
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
