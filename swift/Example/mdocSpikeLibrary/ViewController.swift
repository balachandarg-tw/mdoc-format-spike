//
//  ViewController.swift
//  mdocSpikeLibrary
//
//  Created by BalachandarG on 04/24/2024.
//  Copyright (c) 2024 BalachandarG. All rights reserved.
//

import UIKit
import mdocSpikeLibrary
import SwiftCBOR

class ViewController: UIViewController {
    let library = CborLibrayUtils()
    
    @IBAction func parseMDocCBOR(_ sender: Any) {
        
        let parsedJson = library.decodeAndParseCBOR()
        print("Parsed JSon From Library", parsedJson)
    }
    
    @IBOutlet weak var parseMDocCBOR: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
   

}
