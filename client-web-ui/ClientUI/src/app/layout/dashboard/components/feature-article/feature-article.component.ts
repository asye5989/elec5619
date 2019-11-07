import { Component, OnInit, Input } from "@angular/core";
// This lets me use jquery
declare var $: any;

@Component({
  selector: "feature-article",
  templateUrl: "./feature-article.component.html",
  styleUrls: ["./feature-article.component.scss"]
})
export class FeatureArticleComponent implements OnInit {
  @Input()
  title: string;

  @Input()
  author: string;

  @Input()
  ref: string;

  @Input()
  posted: string;

  @Input()
  src: string;

  @Input()
  intro: string;

  @Input()
  id: string;

  constructor() {}

  ngOnInit() {}

  openWin(): void {
    window.open(this.src, this.title, "height=400,width=400");
  }
}
