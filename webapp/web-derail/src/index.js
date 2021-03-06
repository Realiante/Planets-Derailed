import React from 'react';
import ReactDOM from 'react-dom';
import * as PIXI from 'pixi.js';
import {Stage, Graphics} from '@inlet/react-pixi';
import './index.css';


const App = () => (
    <Stage width={800} height={600} options={{ antialias: true }}>
        <Graphics
            draw={(g) => {
                g.beginFill(0xff3300);
                g.lineStyle(4, 0xffd900, 1);

                g.moveTo(50, 50);
                g.lineTo(250, 50);
                g.lineTo(100, 100);
                g.lineTo(50, 50);
                g.endFill();

                g.lineStyle(2, 0x0000ff, 1);
                g.beginFill(0xff700b, 1);
                g.drawRect(50, 250, 120, 120);

                g.lineStyle(2, 0xff00ff, 1);
                g.beginFill(0xff00bb, 0.25);
                g.drawRoundedRect(150, 450, 300, 100, 15);
                g.endFill();

                g.lineStyle(0);
                g.beginFill(0xffff0b, 0.5);
                g.drawCircle(470, 90, 60);
                g.endFill();
            }}
        />
    </Stage>
);

ReactDOM.render(<App />, document.body);

