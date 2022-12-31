#include <stdio.h>
#define MOVE 0
#define ADD 1
#define SUB 2
#define BRA 3
#define CMP 4
#define BEQ 5
#define BNE 6
#define EXG 7
#define STOP 15

void main(void)
{
    unsigned short int PC = 0;
    unsigned short int D0 = 0;
    unsigned short int A0 = 0;
    unsigned short int CCR = 0;
    unsigned short int MAR;
    unsigned short int MBR;
    unsigned short int IR;
    unsigned short int operand;
    unsigned short int source;
    unsigned short int destination;
    unsigned short int opcode;
    unsigned short int amode;
    unsigned short int direction;
    unsigned short int memory[256];
    unsigned short int run = 1;

    memory[0] = 5;
    memory[1] = 4;
    // memory[4] = 4;
    memory[2] = 240;

    /* MAIN LOOP */
    while (run)
    {
        MAR = PC;
        PC = PC + 1;
        MBR = memory[MAR];
        IR = MBR;
        opcode = IR;
        MAR = PC;
        PC = PC + 1;
        MBR = memory[MAR];
        IR = MBR;
        operand = IR;
        amode = opcode & 0x03;
        direction = (opcode & 0x04) >> 2;

        opcode = opcode >> 4;

        /*Using address mode to find the source operand*/
        switch (amode)
        {
        case 0:
        {
            source = memory[operand];
            break;
        }
        case 1:
        {
            source = operand;
            break;
        }
        case 2:
        {
            source = memory[A0 + operand];
            break;
        }
        case 3:
        {
            source = memory[PC + operand];
            break;
        }
        }

        /*Instruction execution*/
        switch (opcode)
        {
        case MOVE:
        {
            if (direction == 0)
                destination = D0;
            else
                D0 = source;
            if (D0 == 0)
                CCR = 1;
            else
                CCR = 0;
            break;
        }

            /////////////////////////////////////////

        case ADD:
        {
            if (direction == 0)
            {
                destination = D0 + source;
                if (destination == 0)
                    CCR = 1;
                else
                    CCR = 0;
            }
            else
            {
                D0 = D0 + source;
                if (D0 == 0)
                    CCR = 1;
                else
                    CCR = 0;
            }
            break;
        }
        /////////////////////////////////////////
        case SUB:
        {
            if (direction == 0)
            {
                destination = D0 - source;
                if (destination == 0)
                {
                    CCR = 1;
                }
                else
                {
                    CCR = 0;
                }
            }
            else
            {
                D0 = D0 - source;
                if (D0 == 0)
                    CCR = 1;
                else
                {
                    CCR = 0;
                }
            }
            break;
        }
            //////////////////////////////////////

        case BRA:
        {
            if (amode == 0)
            {
                PC = operand;
            }
            if (amode == 1)
            {
                PC = PC + operand;
            }
            break;
        }

            ///////////////////////////////////////////
        case CMP:
        {
            MBR = D0 - source;
            if (MBR == 0)
            {
                CCR = 1;
            }
            else
            {
                CCR = 0;
            }
            break;
        }
        ////////////////////////////////////////
        case BEQ:
        {
            if (CCR == 1)
            {
                if (amode == 0)
                    PC = operand;
                if (amode == 1)
                    PC = PC + operand;
            }
            break;
        }
            /////////////////////////////////////

        case BNE:
        {
            if (CCR != 1)
            {
                if (amode == 0)
                    PC = operand;
                if (amode == 1)
                    PC = PC + operand;
            }
            break;
        }
            ////////////////////////////////////////////

        case EXG:
        {
            MBR = D0;
            D0 = A0;
            A0 = MBR;
            break;
        }

        case STOP:
        {
            run = 0;
            break;
        }
        }

        /* save result in memory if it is register to memory*/
        if (direction == 0)
            switch (amode)
            {
            case 0:
            {
                memory[operand] = destination;
                break;
            }

            case 1:
            {
                break;
            } // literal

            case 2:
            {
                memory[A0 + operand] = destination;
                break;
            }

            case 3:
            {
                memory[PC + operand] = destination;
                break;
            }
            }
    }
    printf("D0 =  ");
    printf("%d\n", D0);
    // printf("CCR =  ");
    // printf("%d", CCR);
}