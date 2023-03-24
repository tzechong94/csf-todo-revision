export interface Task {
    _id: string,
    description: string,
    priority: string,
    due: Date
}


export interface EditedTask {
    index: number,
    description: string,
    priority: string,
    due: Date
}

