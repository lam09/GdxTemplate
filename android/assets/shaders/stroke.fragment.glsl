 #version 120 
                    #ifdef GL_ES
                        precision mediump float; 
                    #endif   
                    varying vec4 v_color; 
                    varying vec2 v_texCoords; 
                    
                    uniform sampler2D u_texture; 
                    uniform mat4 u_projTrans; 
                    
       

                    float groovedAlpha(float r,float a)
                    {
                           // float r =0.006; 
							float rate=r*5000;		
								float rx=r/3.2;  // 3.2 screen aspect
							float GrowedAlpha = a;
							for(int i=0;i<rate;i++)
							{
							    float angle=6.28*i/(rate-1);
						    	vec4 tmp=vec4(texture2D(u_texture, vec2(v_texCoords.x+cos(angle)*rx, v_texCoords.y +sin(angle)*r)));
						        GrowedAlpha = mix(GrowedAlpha, 1.0, tmp.a);
							}
							return GrowedAlpha;
							
                    }
                    
                    void main() { 
                   
                           vec4 color = texture2D(u_texture, v_texCoords).rgba;   
                           float max=0.015;

	                            vec4 c1=vec4(0.0,0.0,0.0,1.0);
	                            vec4 c2=vec4(0.6392,0.498,0.247,1.0);
	                            vec4 c3=vec4(0.0,0.0,0.0,1.0);
	                            c3.a=groovedAlpha(max,color.a);
	                            if(c3.a!=0){
	                            c1.a=groovedAlpha(max*0.4444,color.a);
	                            c2.a=groovedAlpha(max*0.7111,color.a);
	                          
								gl_FragColor = mix(mix(mix(c3,c2,c2.a),c1,c1.a), color, color.a);
						}
						else gl_FragColor=color;
    }
                            
                            
                            
             