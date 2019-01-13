import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {forEach} from "@angular/router/src/utils/collection";
import {findNode} from "@angular/compiler";

@Component({
  selector: 'trafficcontrol-render',
  templateUrl: './render.component.html',
  styleUrls: ['./render.component.sass']
})
export class RenderComponent implements OnInit, AfterViewInit {
  private _city: object = null;

  @Input() set city(city: object){
    this._city = city;

    this.redrawCity();
  }

  get city(){
    return this._city;
  }

  @ViewChild("canvasStreet")
  public canvasStreet: ElementRef;

  public context: CanvasRenderingContext2D;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.setupCanvas();
    let maxX = 9;
    let maxY = 9;
    let size = 50;

    this.drawMesh(maxY, maxX, size);
  }

  private redrawCity(){
    if(this.city != null) {
      const width = this.canvasStreet.nativeElement.width;
      const height = this.canvasStreet.nativeElement.height;
      const nodes = this.city["nodes"];
      const edges = this.city["edges"];

      this.context.clearRect(0, 0, width, height);

      let x = 100;
      let y = 100;

      for(let i = 0; i < edges.length; i++){
        // let startNode = findNode(nodes, edges[i].startNode.id);
        // let endNode = findNode(nodes, edges[i].endNode.id);

        this.drawEllipse(x, y);
        this.drawLineFromTo([x,y], [x+100, y]);
        this.drawEllipse(x+100, y);
      }
    }
  }

  private findNode(nodes: object[], id: number): object{
    for(let i = 0; i < nodes.length; i++){
      if(nodes[i]["id"] == id){
        return nodes[i];
      }
    }

    return null;
  }

  private drawMesh(maxY: number, maxX: number, size: number, xOffset: number = 0, yOffset: number = 0) {
    for (let y = 0; y < maxY; y++) {
      for (let x = 0; x < maxX; x++) {
        this.drawEllipse(size * (x + 1) + xOffset, size * (y + 1) + yOffset);

        if (x < maxX - 1) {
          this.drawLineFromTo([size * (x + 1) + xOffset, size * (y + 1) + yOffset], [size * (x + 2) + xOffset, size * (y + 1) + yOffset]);
        }
        if (y < maxY - 1) {
          this.drawLineFromTo([size * (x + 1) + xOffset, size * (y + 1) + yOffset], [size * (x + 1) + xOffset, size * (y + 2) + yOffset]);
        }
      }
    }
  }

  private drawLineFromTo(from: number[], to: number[]) {
    this.context.beginPath();
    this.context.moveTo(from[0], from[1]);
    this.context.lineTo(to[0], to[1]);
    this.context.stroke();
  }

  private drawEllipse(x:number, y:number) {
    let r = 5;

    this.context.beginPath();
    this.context.ellipse(x, y, r, r, 0, 0, Math.PI * 2, true);
    this.context.stroke();
  }

  private setupCanvas(): void {
    // Get the device pixel ratio, falling back to 1.
    var dpr = window.devicePixelRatio || 1;
    // Get the size of the canvas in CSS pixels.
    var rect = this.canvasStreet.nativeElement.getBoundingClientRect();
    // Give the canvas pixel dimensions of their CSS
    // size * the device pixel ratio.
    this.canvasStreet.nativeElement.width = rect.width * dpr;
    this.canvasStreet.nativeElement.height = rect.height * dpr;
    var ctx = this.canvasStreet.nativeElement.getContext('2d');
    // Scale all drawing operations by the dpr, so you
    // don't have to worry about the difference.
    ctx.scale(dpr, dpr);
    this.context = ctx;
  }


}
