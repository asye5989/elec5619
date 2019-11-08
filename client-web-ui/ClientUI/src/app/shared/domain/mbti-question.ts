export class MbtiQuestion {
  public num: number;
  public questiontext: string;
  public optionA: string;
  public optionB: string;
  public response: string = "";
  constructor(
    num: number,
    questiontext: string,
    optionA: string,
    optionB: string
  ) {
    this.num = num;
    this.questiontext = questiontext;
    this.optionA = optionA;
    this.optionB = optionB;
  }
}
